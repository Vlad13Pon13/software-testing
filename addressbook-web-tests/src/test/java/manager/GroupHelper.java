package manager;

import io.qameta.allure.Step;
import models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupHelper extends HelperBase{

    public GroupHelper(ApplicationManager applicationManager){
        super(applicationManager);

    }

    public void openGroupsPage() {
        click(By.linkText("groups"));
    }

    @Step
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

    @Step
    public void removeOneGroup(GroupData data){
        openGroupsPage();
        selectGroup(data);
        removeSelectedGroup();
        waitElementOnPage(By.linkText("group page"));
        returnToGroupsPage();

    }

    @Step
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



    @Step
    private void selectAllGroup(){
        applicationManager.webDriver
                .findElements(By.name("selected[]"))
                .forEach(WebElement::click);
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

    @Step
    public List<GroupData> getList() {
        openGroupsPage();
        List<WebElement> spans = applicationManager.webDriver.findElements(By.cssSelector("span.group"));
        return spans.stream()
                .map(span ->{
                    String name = span.getText();
                    WebElement checkbox =span.findElement(By.name("selected[]"));
                    String id = checkbox.getAttribute("value");
                    return new GroupData().withHId(id).withName(name);
                })
                .collect(Collectors.toList());



    }
}
