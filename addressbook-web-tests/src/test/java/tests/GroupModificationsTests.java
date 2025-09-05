package tests;

import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GroupModificationsTests extends TestBase{

    @Test()
    public void modifyGroup(){
        if (app.group().getGroupCount()==0){
            app.group().createGroup(new GroupData().withName("forModifyTest"));
        }

        List<GroupData> oldGroups = app.group().getList();
        Random rnd = new Random();
        int index = rnd.nextInt(oldGroups.size());

        GroupData testDate = new GroupData().withName("ModifyTestName");
        app.group().modifyGroup(oldGroups.get(index),testDate);

        List<GroupData> newGroups = app.group().getList();
        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testDate.withHId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

    }

    @Test()
    public void modifyGroupJdbcControl(){
        if (app.group().getGroupCount()==0){
            app.group().createGroup(new GroupData().withName("forModifyTest"));
        }

        List<GroupData> oldGroups = app.jdbcHelper().getGroupListJdbc();
        Random rnd = new Random();
        int index = rnd.nextInt(oldGroups.size());

        GroupData testDate = new GroupData().withName("ModifyTestName");
        app.group().modifyGroup(oldGroups.get(index),testDate);

        List<GroupData> newGroups = app.jdbcHelper().getGroupListJdbc();
        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testDate.withHId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

        //Проверяем что модификация синхронизирована с UI и БД
        var groupsUiListAfterTest = app.group().getList();//Получаем лист UI после модификации
        List<GroupData> newGroupsForCompare = new ArrayList<>(); //Создаем новый лист из запроса БД и заполняем его пустыми header and footer
        for (GroupData data : newGroups) {
            GroupData groupWithEmptyFields = data.withHeader("").withFooter("");
            newGroupsForCompare.add(groupWithEmptyFields);
        }

        newGroupsForCompare.sort(compareById);
        groupsUiListAfterTest.sort(compareById);

        // Сравниваем
        Assertions.assertEquals(newGroupsForCompare, groupsUiListAfterTest);

    }

}
