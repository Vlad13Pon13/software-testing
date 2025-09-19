package ru.stqa.mantis.manager;

import jakarta.mail.*;
import model.MailMessage;


import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class MailHelper extends HelperBase {
    public MailHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public List<MailMessage> receive(String username, String password, Duration duration) {

        var start = System.currentTimeMillis();
        while (System.currentTimeMillis()< start + duration.toMillis()){
            try {
                var inbox = getInbox(username, password);
                inbox.open(Folder.READ_ONLY);
                var messages = inbox.getMessages();

                var result = Arrays.stream(messages).map(m -> {
                    try {
                        return new MailMessage().withFrom(m.getFrom()[0].toString())
                                .withContent((String) m.getContent());
                    } catch (MessagingException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
                inbox.close();
                inbox.getStore().close();
                if(!result.isEmpty()) {
                    return result;
                }

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        }
        throw new RuntimeException("Mail not found");

    }

    private static Folder getInbox(String username, String password) throws MessagingException {
        var session = Session.getInstance(new Properties());
        Store store = session.getStore("pop3");
        try {
            store.connect("localhost", username, password);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        var inbox = store.getFolder("INBOX");
        return inbox;
    }

    public void drain(String username, String password) {
        try {
            var inbox = getInbox(username, password);
            inbox.open(Folder.READ_WRITE);
            Arrays.stream(inbox.getMessages()).forEach(message -> {
                try {
                    message.setFlag(Flags.Flag.DELETED,true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            inbox.close();
            inbox.getStore().close();
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public String extractUrl(List<MailMessage> messages) {
        var text = messages.get(0).from();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            return text.substring(matcher.start(), matcher.end());

        } else {
            throw new RuntimeException("URL не найден в тексте сообщения");

        }

    }
}
