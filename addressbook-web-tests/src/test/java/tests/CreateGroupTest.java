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
import java.util.Set;
import java.util.function.Supplier;
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
    @DisplayName("Создание c провайдером, который берет данные из БД (JDBC)")
    @MethodSource("providerSupplier")
    public void canCreateGroupHbm(GroupData groupData) {

        List<GroupData> oldGroups = app.hmb().getGroupListHbm();

        app.group().createGroup(groupData);

        List<GroupData> newGroups = app.hmb().getGroupListHbm();

        var extraGroups = newGroups.stream().filter(g -> !oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();
        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.add(groupData.withHId(newId));

        Assertions.assertEquals(Set.copyOf(newGroups) ,Set.copyOf(expectedList) );

        //Сравниваем UI с БД после добавлением новой группы
        var groupsUiListAfterTest = app.group().getList();//Получаем лист UI

        List<GroupData> newGroupsForCompare = new ArrayList<>(); //Создаем новый лист и заполняем его пустыми header and footer
        for (GroupData data : newGroups) {
            GroupData groupWithEmptyFields = data.withHeader("").withFooter("");
            newGroupsForCompare.add(groupWithEmptyFields);
        }

        // Сравниваем
        Assertions.assertEquals(Set.copyOf(newGroupsForCompare) ,Set.copyOf(groupsUiListAfterTest));







    }


    @ParameterizedTest
    @MethodSource("negativeGroupCreation")
    public void negativeCreateGroup(GroupData data) {
        var oldGroups = app.group().getList();
        app.group().createGroup(data);
        var newGroups = app.group().getList();
        Assertions.assertEquals(oldGroups, newGroups);

    }



    static Stream<GroupData> providerSupplier() {
        Supplier<GroupData> group = () -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(5))
                .withFooter(CommonFunctions.randomString(2));
         return Stream.generate(group).limit(3);

        }









    static Stream<Arguments> providerGroupData() {
        return Stream.of(
                Arguments.of(new GroupData().withHeader("testHeader").withName(CommonFunctions.randomString(3))),
                Arguments.of(new GroupData().withName("testName").withName(CommonFunctions.randomString(3))),
                Arguments.of(new GroupData().withFooter("testFooter").withName(CommonFunctions.randomString(3)))
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
