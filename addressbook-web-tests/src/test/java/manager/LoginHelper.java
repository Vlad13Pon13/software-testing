package manager;

import org.openqa.selenium.By;

public class LoginHelper {

    private final ApplicationManager applicationManager;

    public LoginHelper(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }


    public void login(String user, String password) {
        applicationManager.webDriver.findElement(By.name("user")).sendKeys(user);
        applicationManager.webDriver.findElement(By.name("pass")).sendKeys(password);
        applicationManager.webDriver.findElement(By.xpath("//input[@value='Login']")).click();
    }

}
