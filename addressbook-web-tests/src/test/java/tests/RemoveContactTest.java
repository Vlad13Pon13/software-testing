package tests;

import coomon.CommonFunctions;
import models.ContactData;
import models.GroupData;
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
            app.contact().createNewContact(new ContactData(
                    CommonFunctions.nameGenerator("male", "firstName"),
                    CommonFunctions.nameGenerator("male", "lastName"),
                    "123 Elm Street",
                    "555-5678",
                    "john.doe@example.com"
            ));
            app.contact().createNewContact(new ContactData(
                    CommonFunctions.nameGenerator("female", "firstName"),
                    CommonFunctions.nameGenerator("female", "lastName"),
                    "123 Elm Street",
                    "555-5678",
                    "Jane.Doe@example.com"));
        } else if(app.contact().countContact() == 1){
            app.contact().createNewContact(new ContactData(
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
            app.contact().createNewContact(new ContactData(
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
            app.contact().createNewContact(new ContactData(
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

    @Test
    @DisplayName("Удаление контакта из группы")
    public void removeContactWithGroup() throws SQLException {
        if (app.jdbcHelper().getGroupCountJdbc() == 0){
            app.group().createGroup(new GroupData().withName("forAddContactInGroup")
            );
        }
        if (app.jdbcHelper().getContactCountJdbc() == 0){
            app.contact().createNewContact(new ContactData(
                    CommonFunctions.nameGenerator("male", "firstName"),
                    CommonFunctions.nameGenerator("male", "lastName"),
                    "123 Elm Street",
                    CommonFunctions.randomPhoneNumber(),
                    "john.doe@example.com"
            ));
        }
        if (app.jdbcHelper().getCountGroupWithContact() == 0 ) { //Если нашли контакт без группы и группу без контакта то связываем их
            var contact = app.jdbcHelper().findContactWithoutGroup().getFirst();//Получили контакт без группы
            var group = app.jdbcHelper().getGroupListWithoutContact().getFirst();//Получли группу без контактов
            app.contact().addContactInGroup(contact, group);//Создали связку группа контакт
        }

        var groupWithContact = app.jdbcHelper().getGroupListWithContact();//Получили список всех групп у которых есть контакты
        Random rnd = new Random();
        var group = groupWithContact.get(rnd.nextInt(groupWithContact.size()));//Выбрали рандомную группу
        var oldContacts = app.jdbcHelper().getContactsInGroups(group);//Получили список контактов в вабранной групе
        var contact = oldContacts.get(rnd.nextInt(oldContacts.size())); //Выбрали случайный контакт, который будем исключать из группы

        app.contact().removeContactInGroup(group, contact);

        var newContacts = app.jdbcHelper().getContactsInGroups(group);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(contact);
        Assertions.assertEquals(newContacts, expectedList);








    }




}
