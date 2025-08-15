package manager;

import models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupHelper {

    private final ApplicationManager applicationManager;

    public GroupHelper(ApplicationManager applicationManager){
        this.applicationManager = applicationManager;

    }

    public void openGroupsPage() {
        if (!applicationManager.group().applicationManager.isElementPresent(By.name("new"))) {
            applicationManager.webDriver.findElement(By.linkText("groups")).click();
        }
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        applicationManager.webDriver.findElement(By.name("new")).click();
        applicationManager.webDriver.findElement(By.name("group_name")).click();
        applicationManager.webDriver.findElement(By.name("group_name")).sendKeys(group.name());
        applicationManager.webDriver.findElement(By.name("group_header")).sendKeys(group.header());
        applicationManager.webDriver.findElement(By.name("group_footer")).sendKeys(group.footer());
        applicationManager.webDriver.findElement(By.name("submit")).click();
        applicationManager.webDriver.findElement(By.linkText("group page")).click();
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return applicationManager.isElementPresent(By.name("selected[]"));
    }

    public void removeAllGroup() {
        openGroupsPage();
        //отмечаем все чек-боксы, если создано несколько тестовых групп
        List<WebElement> checkboxes = applicationManager.webDriver.findElements(By.name("selected[]"));
        for (WebElement checkbox : checkboxes){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }
        applicationManager.webDriver.findElement(By.name("delete")).click();
        applicationManager.webDriver.findElement(By.linkText("group page")).click();
    }
}
