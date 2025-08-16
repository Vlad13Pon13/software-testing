package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperBase {
    protected final ApplicationManager applicationManager;

    public HelperBase(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    protected void click(By locator) {
        applicationManager.webDriver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        applicationManager.webDriver.findElement(locator).clear();
        applicationManager.webDriver.findElement(locator).sendKeys(text);
    }


    protected void waitElementOnPage(By locator){
        WebDriverWait wait = new WebDriverWait(applicationManager.webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }
}
