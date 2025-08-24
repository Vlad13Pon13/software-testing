package tests;

import models.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationsTests extends TestBase{

    @Test()
    public void modifyGroup(){
        if (app.group().getGroupCount()==0){
            app.group().createGroup(new GroupData().withName("forModifyTest"));
        }
        app.group().modifyGroup(new GroupData().withName("ModifyTestName"));


    }

}
