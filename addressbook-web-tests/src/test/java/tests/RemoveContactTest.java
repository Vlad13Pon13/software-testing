package tests;

import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemoveContactTest extends TestBase{


    @Test
    public void removeContactsTest(){
        if(app.contact().countContact() == 0){
            app.contact().createNewContract(new ContactData(
                    "John",
                    "A.",
                    "Doe",
                    "Johnny",
                    "he",
                    "Acme",
                    "123 Elm Street",
                    "555-5678",
                    "john.doe@example.com"
            ));
            app.contact().createNewContract(new ContactData(
                    "Jane",
                    "A.",
                    "Doe",
                    "JaneSun",
                    "she",
                    "Acme",
                    "123 Elm Street",
                    "555-5678",
                    "Jane.Doe@example.com"));
        } else if(app.contact().countContact() == 1){
            app.contact().createNewContract(new ContactData(
                    "Jane",
                    "A.",
                    "Doe",
                    "JaneSun",
                    "she",
                    "Acme",
                    "123 Elm Street",
                    "555-5678",
                    "Jane.Doe@example.com"));

        }
        int contactBeforeTest = app.contact().countContact();
        app.contact().removeAllContact();
        int contactAfterTest = app.contact().countContact();
        Assertions.assertEquals(0, contactAfterTest);
    }



}
