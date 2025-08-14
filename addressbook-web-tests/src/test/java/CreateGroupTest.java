import models.GroupData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class CreateGroupTest extends TestBase{

    @ParameterizedTest
    @DisplayName("Создание групп по 4 сценариям")
    @MethodSource("prviderGroupData")
    public void canCreateGroup(GroupData Data) {
        app.openGroupsPage();
        app.createGroup(Data);

    }

    static Stream<Arguments> prviderGroupData(){
        return Stream.of(
                Arguments.of(new GroupData().withHeader("testHeader")),
                Arguments.of(new GroupData().withName("testName")),
                Arguments.of(new GroupData().withFooter("testFooter")),
                Arguments.of(new GroupData())
        );
    }



}
