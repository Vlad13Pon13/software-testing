package tests;

import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CreateContactTest extends TestBase {

    @ParameterizedTest
    @DisplayName("Добавление контактов")
    @MethodSource("providerContactData")
    public void test(ContactData data){
        int contactBeforeTest = app.contact().countContact();
        app.contact().createNewContract(data);
        int contactAfterTest =app.contact().countContact();
        Assertions.assertEquals(contactBeforeTest + 1, contactAfterTest);

    }


    static Stream<Arguments> providerContactData() {
       ContactData contactOne = new  ContactData(
                        "John",
                        "A.",
                        "Doe",
                        "Johnny",
                        "he",
                        "Acme",
                        "123 Elm Street",
                        randomPhoneNumber(),
                        "john.doe@example.com"
                );

       ContactData contactTwo = new ContactData(
               "Jane",
               "A.",
               "Doe",
               "JaneSun",
               "she",
               "Acme",
               "123 Elm Street",
               randomPhoneNumber(),
               "Jane.Doe@example.com"

       );
       contactTwo.setBirthDay("13");
       contactTwo.setBirthMonth("December");
       contactTwo.setBirthYear("1999");

       return Stream.of(
               Arguments.of(contactOne),
               Arguments.of(contactTwo));

    }





}
