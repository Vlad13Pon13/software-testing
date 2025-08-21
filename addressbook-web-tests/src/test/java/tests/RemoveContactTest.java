package tests;

import models.ContactData;
import org.junit.jupiter.api.Test;

public class RemoveContactTest extends TestBase{


    @Test
    public void removeContactsTest(){
        if(!app.contact().isContactPresent()){
            ContactData data = new ContactData(
                    "John",
                    "A.",
                    "Doe",
                    "Johnny",
                    "he",
                    "Acme",
                    "123 Elm Street",
                    "555-5678",
                    "john.doe@example.com"
            );
            app.contact().createNewContract(data);
        }
        app.contact().removeAllContact();
    }



}
