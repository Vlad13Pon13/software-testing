package tests;

import coomon.CommonFunctions;
import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class RemoveContactTest extends TestBase{


    @Test
    @DisplayName("Удаление нескольких контактов")
    public void removeContactsTest(){
        if(app.contact().countContact() == 0){
            app.contact().createNewContract(new ContactData(
                    CommonFunctions.nameGenerator("male", "firstName"),
                    CommonFunctions.nameGenerator("male", "lastName"),
                    "123 Elm Street",
                    "555-5678",
                    "john.doe@example.com"
            ));
            app.contact().createNewContract(new ContactData(
                    CommonFunctions.nameGenerator("female", "firstName"),
                    CommonFunctions.nameGenerator("female", "lastName"),
                    "123 Elm Street",
                    "555-5678",
                    "Jane.Doe@example.com"));
        } else if(app.contact().countContact() == 1){
            app.contact().createNewContract(new ContactData(
                    "Jane",
                    "Doe",
                    "123 Elm Street",
                    "555-5678",
                    "Jane.Doe@example.com"));

        }
        int contactBeforeTest = app.contact().countContact();
        app.contact().removeAllContact();
        int contactAfterTest = app.contact().countContact();
        Assertions.assertEquals(0, contactAfterTest);
    }

    @Test()
    @DisplayName("Удаление одного контакта")
    public void removeOneContact(){
        if (app.contact().countContact() == 0){
            app.contact().createNewContract(new ContactData(
                    "Jane",
                    "Doe",
                    "123 Elm Street",
                    "555-5678",
                    "Jane.Doe@example.com"));
        }
        var oldContacts = app.contact().getList();
        Random rnd = new Random();
        int index = new Random().nextInt(oldContacts.size());

        app.contact().removeContact();
        var newList = app.contact().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);

        Assertions.assertEquals(newList, expectedList);

    }



}
