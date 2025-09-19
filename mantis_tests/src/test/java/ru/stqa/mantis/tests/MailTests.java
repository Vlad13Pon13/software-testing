package ru.stqa.mantis.tests;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class MailTests extends TestBase{

    @Test
    public void  canReceiveMail(){
        var messages = app.mail().receive("u2@localhost", "password", Duration.ofSeconds(10));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }

    @Test
    public void drainTest() throws MessagingException {
        app.mail().drain("user1@localhost", "password");
    }
}
