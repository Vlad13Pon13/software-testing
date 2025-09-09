package tests;

import coomon.CommonFunctions;
import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationsTest extends TestBase {

    @Test
    @DisplayName("Изменение существующего контакта")
    public void modifyContactTest() throws SQLException {
        if (app.contact().countContact() == 0) {
            app.contact().createNewContract(new ContactData(
                    CommonFunctions.nameGenerator("male", "firstName"),
                    CommonFunctions.nameGenerator("male", "lastName"),
                    "123 Elm Street",
                    CommonFunctions.randomPhoneNumber(),
                    "john.doe@example.com"
            ));
        }

        var oldContacts = app.jdbcHelper().getContactListJdbc();
        Random rnd = new Random();
        int index = rnd.nextInt(oldContacts.size());

        ContactData modifyData = new ContactData(
                CommonFunctions.nameGenerator("male", "firstName"),
                CommonFunctions.nameGenerator("male", "lastName"),
                "123 Elm Street",
                CommonFunctions.randomPhoneNumber(),
                "john.doe@example.com");

        modifyData.setId(oldContacts.get(index).getId());
        modifyData.setMobile(oldContacts.get(index).getMobile());
        app.contact().modifyContact(oldContacts.get(index),modifyData);

        var newContacts = app.jdbcHelper().getContactListJdbc();
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
