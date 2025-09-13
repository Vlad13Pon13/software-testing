package tests;

import coomon.CommonFunctions;
import models.ContactData;
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
        if (app.contact().countContact()==0){
            var contact = new ContactData(CommonFunctions.nameGenerator("male", "firstName"),
                    CommonFunctions.nameGenerator("male", "lastName"),
                    "123 Elm Street",
                    CommonFunctions.randomPhoneNumber(),
                    "john.doe@example.com");
            contact.setHome(CommonFunctions.randomPhoneNumber());
            app.contact().createNewContact(contact);
        }

        Random rnd = new Random();
        var contacts = app.hmb().getContactListHbm();
        var contact = contacts.get(rnd.nextInt(contacts.size()));
        var phones = app.contact().getPhones(contact);
        var expect = Stream.of(contact.getHome(), contact.getMobile(), contact.getWork())
                .filter(s -> s != null &&  ! "".equals(s))
                .map(s -> s.replaceAll("\\D+", ""))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expect, phones);

    }

    @Test
    @DisplayName("Проверка Номера")
    public void phoneNumberTestWithMap(){
        if (app.contact().countContact()==0){
            var contact = new ContactData(CommonFunctions.nameGenerator("male", "firstName"),
                    CommonFunctions.nameGenerator("male", "lastName"),
                    "123 Elm Street",
                    CommonFunctions.randomPhoneNumber(),
                    "john.doe@example.com");
            contact.setHome(CommonFunctions.randomPhoneNumber());
            app.contact().createNewContact(contact);
        }

        var contacts = app.hmb().getContactListHbm();
        var expect = contacts.stream().collect(Collectors.toMap(ContactData::getId, contact ->
            Stream.of(contact.getHome(), contact.getMobile(), contact.getWork())
                    .filter(s -> s != null &&  ! "".equals(s))
                    .map(s -> s.replaceAll("\\D+", ""))
                    .collect(Collectors.joining("\n"))
        ));
        var phones = app.contact().getPhones();
        Assertions.assertEquals(expect, phones);

    }

    @Test
    @DisplayName("Проверка Почты")
    public void emailTestWithMap(){
        if (app.contact().countContact()==0){
            var contact = new ContactData(CommonFunctions.nameGenerator("male", "firstName"),
                    CommonFunctions.nameGenerator("male", "lastName"),
                    "123 Elm Street",
                    CommonFunctions.randomPhoneNumber(),
                    CommonFunctions.randomEmail());
            contact.setMail(CommonFunctions.randomEmail());
            contact.setMailTwo(CommonFunctions.randomEmail());
            app.contact().createNewContact(contact);

            var contactTwo = new ContactData(CommonFunctions.nameGenerator("male", "firstName"),
                    CommonFunctions.nameGenerator("male", "lastName"),
                    "123 Elm Street",
                    CommonFunctions.randomPhoneNumber(),
                    CommonFunctions.randomEmail());
            contactTwo.setMail(CommonFunctions.randomEmail());
            contactTwo.setMailThree(CommonFunctions.randomEmail());
            app.contact().createNewContact(contactTwo);
        }

        var contacts = app.hmb().getContactListHbm();
        var expect = contacts.stream().collect(Collectors.toMap(ContactData::getId, contact ->
                Stream.of(contact.getMail(), contact.getMailTwo(), contact.getMailThree())
                        .filter(s -> s != null &&  ! "".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var emails = app.contact().getEmails();
        Assertions.assertEquals(expect, emails);

    }

    @Test
    @DisplayName("Проверка Адреса")
    public void addressTestWithMap(){
        if (app.contact().countContact()==0){
            var contact = new ContactData(CommonFunctions.nameGenerator("male", "firstName"),
                    CommonFunctions.nameGenerator("male", "lastName"),
                    CommonFunctions.randomAddress(),
                    CommonFunctions.randomPhoneNumber(),
                    CommonFunctions.randomEmail());
            contact.setMail(CommonFunctions.randomEmail());
            contact.setMailTwo(CommonFunctions.randomEmail());
            app.contact().createNewContact(contact);

            var contactTwo = new ContactData(CommonFunctions.nameGenerator("male", "firstName"),
                    CommonFunctions.nameGenerator("male", "lastName"),
                    CommonFunctions.randomAddress(),
                    CommonFunctions.randomPhoneNumber(),
                    CommonFunctions.randomEmail());
            contactTwo.setMail(CommonFunctions.randomEmail());
            contactTwo.setMailThree(CommonFunctions.randomEmail());
            app.contact().createNewContact(contactTwo);
        }

        var contacts = app.hmb().getContactListHbm();
        var expect = contacts.stream().collect(Collectors.toMap(ContactData::getId, contact ->
                Stream.of(contact.getAddress())
                        .filter(s -> s != null &&  ! "".equals(s))
                        .collect(Collectors.joining(""))
        ));
        var address = app.contact().getAddress();
        Assertions.assertEquals(expect, address);

    }

}
