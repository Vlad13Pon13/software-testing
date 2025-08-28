package tests;

import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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


}
