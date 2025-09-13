package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactsInfoTests extends  TestBase{

    @Test
    @DisplayName("Проверка Номера")
    public void phoneNumberTest(){
        //TODO дописать предусловие

        Random rnd = new Random();
        var contacts = app.hmb().getContactListHbm();
        var contact = contacts.get(rnd.nextInt(contacts.size()));
        var phones = app.contact().getPhones(contact);
        var expect = Stream.of(contact.getHome(), contact.getMobile(), contact.getWork())
                .filter(s -> s != null &&  ! "".equals(s))
                .collect(Collectors.joining("\n")).replaceAll("\\D+", "");
        Assertions.assertEquals(expect, phones);

    }
}
