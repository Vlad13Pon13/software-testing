package tests;

import models.ContactData;
import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class CreateContactTest extends TestBase {

    @ParameterizedTest
    @DisplayName("Добавление контактов")
    @MethodSource("providerContactData")
    public void test(ContactData data){
        var oldContact = app.contact().getList();
        app.contact().createNewContract(data);
        var newContact = app.contact().getList();

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.getId()), Integer.parseInt(o2.getId()));
        };
        newContact.sort(compareById);

        var expectedList = new ArrayList<>(oldContact);
        data.setId(newContact.getLast().id);
        data.setMobile(newContact.getLast().mobile);
        expectedList.add(data);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact,expectedList);


    }


    static Stream<Arguments> providerContactData() {
       ContactData contactOne = new  ContactData(
                        "John",
                        "Doe",
                        "123 Elm Street",
                        randomPhoneNumber(),
                        "john.doe@example.com"
                );

       ContactData contactTwo = new ContactData(
               "Jane",
               "Doe",
               "123 Elm Street",
               randomPhoneNumber(),
               "Jane.Doe@example.com"

       );

       return Stream.of(
               Arguments.of(contactOne),
               Arguments.of(contactTwo));

    }





}
