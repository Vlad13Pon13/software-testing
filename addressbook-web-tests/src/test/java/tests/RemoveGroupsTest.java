package tests;

import models.GroupData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RemoveGroupsTest extends TestBase {

    @Test()
    @DisplayName("Тест удаления всех групп контактов")
    public void removeGroup() {
        if (!app.group().isGroupPresent()) {
            app.group().createGroup(new GroupData().withName("ifEmptyGroupList"));
        }
        app.group().removeAllGroup();
    }


}
