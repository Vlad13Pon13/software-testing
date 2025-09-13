package ru.stqa.mantis.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    private WebDriver webDriver;
    private String browser;
    private Properties properties;
    private SessionHelper session;

    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;

    }

    public WebDriver webDriver(){
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
            webDriver.get(properties.getProperty("web.baseUrl"));
            webDriver.manage().window().fullscreen();
        }
        return webDriver;
    }

    public SessionHelper session(){
        if (session == null){
            session = new SessionHelper(this);
        }
        return session;
    }
}
