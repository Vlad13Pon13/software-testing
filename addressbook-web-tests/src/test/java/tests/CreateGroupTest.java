package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import coomon.CommonFunctions;
import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


public class CreateGroupTest extends TestBase {

    @ParameterizedTest
    @DisplayName("Создание групп по 4 сценариям")
    @MethodSource("providerGroupData")
    public void canCreateGroup(GroupData Data) {
        int groupCountBefore = app.group().getGroupCount();
        app.group().createGroup(Data);
        int groupCountAfter = app.group().getGroupCount();

        Assertions.assertEquals(groupCountBefore + 1, groupCountAfter);
        System.out.println(groupCountBefore);
        System.out.println(groupCountAfter);

    }

    @ParameterizedTest
    @DisplayName("Создание провайдера с циклами и вложенными циклами")
    @MethodSource("positiveGroupNameProvider")
    public void canCreateGroupCycle(GroupData groupData) {

        List<GroupData> oldGroups = app.group().getList();

        app.group().createGroup(groupData);

        List<GroupData> newGroups = app.group().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);

        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.add(groupData.withHId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);


    }



    @ParameterizedTest
    @DisplayName("Создание c провайдером, который берет данные из файла ")
    @MethodSource("readFileProvider")
    public void canCreateGroupJsonProvider(GroupData groupData) {

        List<GroupData> oldGroups = app.group().getList();

        app.group().createGroup(groupData);

        List<GroupData> newGroups = app.group().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);

        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.add(groupData.withHId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);


    }

    @ParameterizedTest
    @DisplayName("Создание c провайдером, который берет данные из БД")
    @MethodSource("providerJdbc")
    public void canCreateGroupJdbc(GroupData groupData) {

        List<GroupData> oldGroups = app.jdbcHelper().getGroupListJdbc();

        app.group().createGroup(groupData);

        List<GroupData> newGroups = app.jdbcHelper().getGroupListJdbc();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);

        var maxId = newGroups.get(newGroups.size() - 1).id();
        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.add(groupData.withHId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

        //Сравниваем UI с БД после добавлением новой группы
        var groupsUiListAfterTest = app.group().getList();//Получаем лист UI

        List<GroupData> newGroupsForCompare = new ArrayList<>(); //Создаем новый лист и заполняем его пустыми header and footer
        for (GroupData data : newGroups) {
            GroupData groupWithEmptyFields = data.withHeader("").withFooter("");
            newGroupsForCompare.add(groupWithEmptyFields);
        }
        // Сортируем списки
        newGroupsForCompare.sort(compareById);
        groupsUiListAfterTest.sort(compareById);
        // Сравниваем
        Assertions.assertEquals(newGroupsForCompare, groupsUiListAfterTest);







    }


    @ParameterizedTest
    @MethodSource("negativeGroupCreation")
    public void negativeCreateGroup(GroupData data) {
        var oldGroups = app.group().getList();
        app.group().createGroup(data);
        var newGroups = app.group().getList();
        Assertions.assertEquals(oldGroups, newGroups);

    }



    static List<GroupData> providerJdbc() {
         return List.of(new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(5))
                .withFooter(CommonFunctions.randomString(2)));


        }









    static Stream<Arguments> providerGroupData() {
        return Stream.of(
                Arguments.of(new GroupData().withHeader("testHeader")),
                Arguments.of(new GroupData().withName("testName")),
                Arguments.of(new GroupData().withFooter("testFooter")),
                Arguments.of(new GroupData())
        );
    }

    static List<GroupData> positiveGroupNameProvider() {
        ArrayList<GroupData> list = new ArrayList<>();
        for (String name : List.of("", "group_name")) {
            for (String header : List.of("", "header_name")) {
                for (String footer : List.of("", "footer_name")) {
                    list.add(new GroupData("", name, footer, header));
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            list.add(new GroupData().
                    withName(CommonFunctions.randomString(i * 10)).
                    withHeader(CommonFunctions.randomString(i * 10)).
                    withFooter(CommonFunctions.randomString(i * 10)));
        }

        return list;
    }

    static List<GroupData> negativeGroupCreation() {
        ArrayList<GroupData> listData = new ArrayList<>(List.of(
                new GroupData().withName("group_name'").withHeader(CommonFunctions.randomString(5)).withFooter(CommonFunctions.randomString(5))
        ));
        return listData;
    }

    static List<GroupData> readFileProvider() throws IOException {
        var result = new ArrayList<GroupData>();
        var resultFile = mapper.readValue(new File("groupsData.json"), new TypeReference<List<GroupData>>() {});
        result.addAll(resultFile);
        return result;


    }


}
