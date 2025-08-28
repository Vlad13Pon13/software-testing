package tests;

import models.ContactData;
import models.GroupData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactModificationsTest extends TestBase{

    @Test
    @DisplayName("Изменение существующего контакта")
    public void modifyContactTest(){
        if (app.contact().countContact() == 0){
            app.contact().createNewContract(new ContactData(
                    "John",
                    "A.",
                    "Doe",
                    "Johnny",
                    "he",
                    "Acme",
                    "123 Elm Street",
                    randomPhoneNumber(),
                    "john.doe@example.com"
            ));

        }
        app.contact().modifyContact(new ContactData("John",
                "A.",
                "Doe",
                "Johnny_123",
                "he",
                "Acme",
                "123 Elm Street",
                randomPhoneNumber(),
                "john.doe@example.com"));

    }


}
