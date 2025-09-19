package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase{
    public SessionHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void login(String user, String pass) {
        type(By.name("username"),user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"),pass);
        click(By.cssSelector("input[type='submit']"));

    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.Name-info"));
    }
}
