package tests;

import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationsTest extends TestBase {

    @Test
    @DisplayName("Изменение существующего контакта")
    public void modifyContactTest() {
        if (app.contact().countContact() == 0) {
            app.contact().createNewContract(new ContactData(
                    "John",
                    "Doe",
                    "123 Elm Street",
                    randomPhoneNumber(),
                    "john.doe@example.com"
            ));
        }

        var oldContacts = app.contact().getList();
        Random rnd = new Random();
        int index = rnd.nextInt(oldContacts.size());

        ContactData modifyData = new ContactData(
                generateRandomName("John"),
                "Doe",
                "123 Elm Street",
                randomPhoneNumber(),
                "john.doe@example.com");

        modifyData.setId(oldContacts.get(index).getId());
        modifyData.setMobile(oldContacts.get(index).getMobile());
        app.contact().modifyContact(oldContacts.get(index),modifyData);

        var newContacts = app.contact().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, modifyData);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.getId()), Integer.parseInt(o2.getId()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);

        Assertions.assertEquals(newContacts, expectedList);

    }

}
