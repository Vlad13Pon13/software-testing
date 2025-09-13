package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

public class HelperBase {

    protected final ApplicationManager applicationManager;

    public HelperBase(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    protected void click(By locator) {
        applicationManager.webDriver().findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        applicationManager.webDriver().findElement(locator).clear();
        applicationManager.webDriver().findElement(locator).sendKeys(text);

    }

    protected void waitElementOnPage(By locator){
        WebDriverWait wait = new WebDriverWait(applicationManager.webDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    protected void selectElementDate(By locator, String date){
        WebElement dropdown = applicationManager.webDriver().findElement(locator);
        Select select = new Select(dropdown);
        select.selectByValue(date);


    }

    protected boolean isElementPresent(By locator){
        return applicationManager.webDriver().findElements(locator).size() > 0;

    }

}
