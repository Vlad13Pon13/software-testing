package tests;

import models.ContactData;
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

        app.contact().createNewContract(data);

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
                        "555-5678",
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
               "555-5678",
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
