package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import coomon.CommonFunctions;
import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class CreateContactTest extends TestBase {

    @ParameterizedTest
    @DisplayName("Добавление контактов")
    @MethodSource("providerContactData")
    public void testContact(ContactData data) throws SQLException {
        var oldContact = app.jdbcHelper().getContactListJdbc();
        app.contact().createNewContract(data);
        var newContact =  app.jdbcHelper().getContactListJdbc();

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.getId()), Integer.parseInt(o2.getId()));
        };
        newContact.sort(compareById);

        var expectedList = new ArrayList<>(oldContact);
        data.setId(newContact.getLast().id);
        data.setMobile(newContact.getLast().mobile);
        data.setPhoto(newContact.getLast().photo);
        expectedList.add(data);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact, expectedList);

    }


    @ParameterizedTest
    @DisplayName("Добавление контактов через провайдера файлов")
    @MethodSource("readFileProvider")
    public void testContactProviderFiles(ContactData data) {
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
        data.setPhoto(newContact.getLast().photo);
        expectedList.add(data);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact, expectedList);


    }

    static List<ContactData> readFileProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        var resultFile = mapper.readValue(new File("resultContact.json"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(resultFile);
        return result;


    }


    static Stream<Arguments> providerContactData() {
        ContactData contactOne = new ContactData(
                CommonFunctions.nameGenerator("male", "firstName"),
                CommonFunctions.nameGenerator("male", "lastName"),
                "123 Elm Street",
                CommonFunctions.randomPhoneNumber(),
                "john.doe@example.com"
        );
        contactOne.setPhoto(CommonFunctions.randomPhoto("src/test/resources/images"));

        ContactData contactTwo = new ContactData(
                CommonFunctions.nameGenerator("female", "firstName"),
                CommonFunctions.nameGenerator("female", "lastName"),
                "123 Elm Street",
                CommonFunctions.randomPhoneNumber(),
                "Jane.Doe@example.com"

        );
        contactTwo.setPhoto(CommonFunctions.randomPhoto("src/test/resources/images"));

        return Stream.of(
                Arguments.of(contactOne),
                Arguments.of(contactTwo));

    }


}
