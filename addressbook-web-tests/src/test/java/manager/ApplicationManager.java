package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    protected   WebDriver webDriver;
    private LoginHelper session;
    private GroupHelper group;


    final String url = "http://localhost/addressbook/";

    public void init() {
        if (webDriver == null){
            webDriver = new FirefoxDriver();
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
}
