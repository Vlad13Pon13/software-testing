package tests;

import coomon.CommonFunctions;
import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
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
        app.contact().removeAllContact();
        int contactAfterTest = app.contact().countContact();
        Assertions.assertEquals(0, contactAfterTest);
    }

    @Test()
    @DisplayName("Удаление одного контакта, использую JDBC helper")
    public void removeOneContactJdbc() throws SQLException {
        if (app.jdbcHelper().getContactCountJdbc()==0){
            app.contact().createNewContract(new ContactData(
                    "Jane",
                    "Doe",
                    "123 Elm Street",
                    "555-5678",
                    "Jane.Doe@example.com"));
        }
        var oldContacts = app.jdbcHelper().getContactListJdbc();
        Random rnd = new Random();
        int index = rnd.nextInt(oldContacts.size());

        app.contact().removeContact();
        var newList = app.jdbcHelper().getContactListJdbc();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);

        Assertions.assertEquals(newList, expectedList);

    }


    @Test()
    @DisplayName("Удаление одного контакта, использую hbm helper")
    public void removeOneContactHbm() {
        if (app.hmb().getContactCount()==0){
            app.contact().createNewContract(new ContactData(
                    "Jane",
                    "Doe",
                    "123 Elm Street",
                    "555-5678",
                    "Jane.Doe@example.com"));
        }
        var oldContacts = app.hmb().getContactListHbm();
        Random rnd = new Random();
        int index = rnd.nextInt(oldContacts.size());

        app.contact().removeContact();
        var newList = app.hmb().getContactListHbm();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);

        Assertions.assertEquals(newList, expectedList);

    }



}
