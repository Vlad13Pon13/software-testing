package tests;

import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RemoveGroupsTest extends TestBase {

    @Test()
    @DisplayName("Тест удаления всех групп контактов")
    public void removeGroup() {
        if (app.group().getGroupCount() == 0) {
            app.group().createGroup(new GroupData().withName("ifEmptyGroupList"));
            app.group().createGroup(new GroupData().withName("ifEmptyGroupListTwo"));
        } else if (app.group().getGroupCount() == 1) {
            app.group().createGroup(new GroupData().withName("ifEmptyGroupListTwo"));
        }
        int countGroupBeforeTest = app.group().getGroupCount();
        app.group().removeAllGroup();
        int countGroupAfterTest = app.group().getGroupCount();
        System.out.println("Количество групп до проведения теста: " + countGroupBeforeTest);
        System.out.println("Количество групп после массового удаления: " + countGroupAfterTest);
        Assertions.assertEquals(0, countGroupAfterTest);
    }

    @Test
    @DisplayName("Удаление одной группы из списка")
    public void removeOneGroup(){
        if (app.group().getGroupCount()==0){
            app.group().createGroup(new GroupData().withName("forDeleteFirstGroup"));
        }
        List<GroupData> oldGroups = app.group().getList();

        Random rnd = new Random();
        int index = rnd.nextInt(oldGroups.size());
        app.group().removeOneGroup(oldGroups.get(index));

        List<GroupData> newGroups = app.group().getList();
        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);

    }

    @Test
    @DisplayName("Удаление одной группы из списка(контроль с помощью JDBC)")
    public void removeOneGroupJdbcControl() throws SQLException {
        if (app.jdbcHelper().getGroupCountJdbc()== 0){
            app.group().createGroup(new GroupData().withName("forDeleteFirstGroup"));
        }
        List<GroupData> oldGroups = app.jdbcHelper().getGroupListJdbc();

        Random rnd = new Random();
        int index = rnd.nextInt(oldGroups.size());
        app.group().removeOneGroup(oldGroups.get(index));

        List<GroupData> newGroups = app.jdbcHelper().getGroupListJdbc();

        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);

        //Проверяем UI и БД после теста
        var groupsUiListAfterTest = app.group().getList();//Получаем лист UI
        List<GroupData> newGroupsForCompare = new ArrayList<>(); //Создаем новый лист из запроса БД и заполняем его пустыми header and footer
        for (GroupData data : newGroups) {
            GroupData groupWithEmptyFields = data.withHeader("").withFooter("");
            newGroupsForCompare.add(groupWithEmptyFields);
        }
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroupsForCompare.sort(compareById);
        groupsUiListAfterTest.sort(compareById);

        // Сравниваем
        Assertions.assertEquals(newGroupsForCompare, groupsUiListAfterTest);

    }

    @Test
    @DisplayName("Удаление одной группы из списка(контроль с помощью HBM)")
    public void removeOneGroupHbmControl(){
        if (app.hmb().getGroupCount()==0){
            app.hmb().createGroupHbm(new GroupData("", "group_name", "group_header", "group_footer"));
        }
        List<GroupData> oldGroups = app.hmb().getGroupListHbm();

        Random rnd = new Random();
        int index = rnd.nextInt(oldGroups.size());
        app.group().removeOneGroup(oldGroups.get(index));

        List<GroupData> newGroups = app.hmb().getGroupListHbm();

        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);

        //Проверяем UI и БД после теста
        var groupsUiListAfterTest = app.group().getList();//Получаем лист UI
        List<GroupData> newGroupsForCompare = new ArrayList<>(); //Создаем новый лист из запроса БД и заполняем его пустыми header and footer
        for (GroupData data : newGroups) {
            GroupData groupWithEmptyFields = data.withHeader("").withFooter("");
            newGroupsForCompare.add(groupWithEmptyFields);
        }
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroupsForCompare.sort(compareById);
        groupsUiListAfterTest.sort(compareById);

        // Сравниваем
        Assertions.assertEquals(newGroupsForCompare, groupsUiListAfterTest);

    }


}
