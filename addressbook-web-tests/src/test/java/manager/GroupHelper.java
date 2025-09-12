package manager;

import models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase{

    public GroupHelper(ApplicationManager applicationManager){
        super(applicationManager);

    }

    public void openGroupsPage() {
        click(By.linkText("groups"));
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        waitElementOnPage(By.name("group_name")); // ожидаем пока элемент group_name прогрузится на страницу
        fillGroupForm(group);
        submitGroupCreation();
        waitElementOnPage(By.linkText("group page"));// ожидаем пока элемент group page прогрузится на страницу
        returnToGroupsPage();
    }


    public void removeAllGroup() {
        openGroupsPage();
        selectAllGroup();//отмечаем все чек-боксы, если создано несколько тестовых групп
        removeSelectedGroup();
        waitElementOnPage(By.linkText("group page"));// ожидаем пока элемент group page прогрузится на страницу
        returnToGroupsPage();
    }

    public void removeOneGroup(GroupData data){
        openGroupsPage();
        selectGroup(data);
        removeSelectedGroup();
        waitElementOnPage(By.linkText("group page"));
        returnToGroupsPage();

    }

    public void modifyGroup(GroupData group, GroupData modyfyGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modyfyGroup);
        submitGroupModification();
        waitElementOnPage(By.linkText("group page"));// ожидаем пока элемент group page прогрузится на страницу
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

    private void selectGroup(GroupData data) {
        click(By.cssSelector(String.format("input[value='%s']",data.id())));
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

    public int getGroupCount() {
        openGroupsPage();
        return applicationManager.webDriver.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getList() {
        openGroupsPage();
        ArrayList<GroupData> groups = new ArrayList<>();
        List<WebElement> spans = applicationManager.webDriver.findElements(By.cssSelector("span.group"));
        for(WebElement span : spans) {
            String name = span.getText();
            WebElement checkbox =span.findElement(By.name("selected[]"));
            String id = checkbox.getAttribute("value");
            groups.add(new GroupData().withHId(id).withName(name));
        }
        return groups;


    }
}
