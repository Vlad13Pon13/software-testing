package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationManager {
    protected   WebDriver webDriver;
    private LoginHelper session;
    private GroupHelper group;


    final String url = "http://localhost/addressbook/";

    public void init(String browser) {
        if (webDriver == null){
            if ("firefox".equals(browser)){
                webDriver = new FirefoxDriver();
            } else if ("chrome".equals(browser)){
                webDriver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser - %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(webDriver::quit)); //дополнительно изучить для понимания
            //Авторизация
            webDriver.get(url);
            webDriver.manage().window().fullscreen();
            session().login("admin", "secret");
        }
    }

    public LoginHelper session(){
        if (session == null){
            session = new LoginHelper(this);
        }
        return session;
    }

    public  GroupHelper group(){
        if (group == null){
            group = new GroupHelper(this);
        }
        return group;
    }

    public boolean isElementPresent(By locator) {
        try {
            webDriver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }

    }

    public void waitElementOnPage(String value, String located){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        switch (value) {
            case "linkText" -> wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(located)));
            case "name" -> wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(located)));
            case "xpath" -> wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(located)));
            case "cssSelector" -> wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(located)));
            case null, default ->
                    throw new IllegalArgumentException(String.format("Передан неизвестный метод поиска элемента - %s", value));
        }
    }

}

