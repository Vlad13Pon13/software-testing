package tests;

import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class CreateGroupTest extends TestBase{

    @ParameterizedTest
    @DisplayName("Создание групп по 4 сценариям")
    @MethodSource("providerGroupData")
    public void canCreateGroup(GroupData Data) {
        int groupCountBefore= app.group().getGroupCount();
        app.group().createGroup(Data);
        int groupCountAfter = app.group().getGroupCount();

        Assertions.assertEquals(groupCountBefore + 1, groupCountAfter);
        System.out.println(groupCountBefore);
        System.out.println(groupCountAfter);

    }

    @ParameterizedTest
    @DisplayName("Создание провайдера с циклами и вложенными циклами")
    @MethodSource("positiveGroupNameProvider")
    public void canCreateGroupCycle(GroupData groupData){

        int countGroupBefore = app.group().getGroupCount();

        app.group().createGroup(groupData);

        int countGroupAfter = app.group().getGroupCount();
        Assertions.assertEquals( countGroupBefore + 1 , countGroupAfter);

    }

    @ParameterizedTest
    @MethodSource("negativeGroupCreation")
    public void negativeCreateGroup(GroupData data){
        int countBeforeTest = app.group().getGroupCount();
        app.group().createGroup(data);
        int countAfterTest = app.group().getGroupCount();
        Assertions.assertEquals(countBeforeTest, countAfterTest);

    }

    static Stream<Arguments> providerGroupData(){
        return Stream.of(
                Arguments.of(new GroupData().withHeader("testHeader")),
                Arguments.of(new GroupData().withName("testName")),
                Arguments.of(new GroupData().withFooter("testFooter")),
                Arguments.of(new GroupData())
        );
    }

    static List<GroupData> positiveGroupNameProvider(){
        ArrayList<GroupData> list = new ArrayList<>();
        for (String name : List.of("", "group_name")){
            for (String header : List.of( "", "header_name")){
                for (String footer : List.of("", "footer_name")){
                    list.add(new GroupData(name,footer,header));
                }
            }
        }

        for (int i = 0; i<5; i++){
            list.add(new GroupData(randomString(i * 10), randomString(i * 10),randomString( i * 10)));
        }

        return list;
    }

    static List<GroupData> negativeGroupCreation(){
        ArrayList<GroupData> listData = new ArrayList<>(List.of(
                new GroupData("group_name'", randomString(5), randomString(5))

        ));
        return listData;
    }





}
