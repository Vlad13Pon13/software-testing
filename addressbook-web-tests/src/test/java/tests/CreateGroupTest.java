package tests;

import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
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

    @Test
    public void canCreateGroupCycle(){
        int n = 3;
        int countGroupBefore = app.group().getGroupCount();

        for (int i = 0; i < n; i++){
            app.group().createGroup(new GroupData().withName(randomString(new Random().nextInt(26))));
        }

        int countGroupAfter = app.group().getGroupCount();
        Assertions.assertEquals( countGroupBefore + n, countGroupAfter);



    }

    static Stream<Arguments> providerGroupData(){
        return Stream.of(
                Arguments.of(new GroupData().withHeader("testHeader")),
                Arguments.of(new GroupData().withName("testName")),
                Arguments.of(new GroupData().withFooter("testFooter")),
                Arguments.of(new GroupData())
        );
    }



}
