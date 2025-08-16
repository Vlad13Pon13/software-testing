package manager;

import models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupHelper extends HelperBase{

    public GroupHelper(ApplicationManager applicationManager){
        super(applicationManager);

    }

    public void openGroupsPage() {
        if (!applicationManager.group().applicationManager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
   //     waitElementOnPage(By.name("group_name")); // ожидаем пока элемент group_name прогрузится на страницу
        fillGroupForm(group);
        submitGroupCreation();
 //       waitElementOnPage(By.linkText("group page"));// ожидаем пока элемент group page прогрузится на страницу
        returnToGroupsPage();
    }


    public void removeAllGroup() {
        openGroupsPage();
        selectAllGroup();//отмечаем все чек-боксы, если создано несколько тестовых групп
        removeSelectedGroup();
 //       waitElementOnPage(By.linkText("group page"));// ожидаем пока элемент group page прогрузится на страницу
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData modyfyGroup) {
        openGroupsPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modyfyGroup);
        submitGroupModification();
    //    waitElementOnPage(By.linkText("group page"));// ожидаем пока элемент group page прогрузится на страницу
        returnToGroupsPage();
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return applicationManager.isElementPresent(By.name("selected[]"));
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());

    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }


    private void selectAllGroup(){
        List<WebElement> checkboxes = applicationManager.webDriver.findElements(By.name("selected[]"));
        for (WebElement checkbox : checkboxes){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void removeSelectedGroup() {
        click(By.name("delete"));
    }

}
