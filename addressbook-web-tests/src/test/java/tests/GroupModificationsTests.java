package tests;

import models.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationsTests extends TestBase{

    @Test()
    public void modifyGroup(){
        if (!app.group().isGroupPresent()){
            app.group().createGroup(new GroupData().withName("forModifyTest"));
        }
        app.group().modifyGroup(new GroupData().withName("ModyfyTestName"));


    }

}
