import models.GroupData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RemoveGroupsTest extends TestBase {

    @Test()
    @DisplayName("Тест удаления всех групп контактов")
    public void removeGroup() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup(new GroupData());
        }
        removeAllGroup();
    }


}
