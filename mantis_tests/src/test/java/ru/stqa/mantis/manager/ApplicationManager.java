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
    private HttpSessionHelper httpSessionHelper;
    private JamesHelper jamesCliHelper;
    private MailHelper mailHelper;
    private UserHelper userHelper;
    private JamesApiHelper jamesApiHelper;
    private RestApiHelper restApiHelper;
    private SoapApiHelper soapApiHelper;

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

    public HttpSessionHelper http(){
        if (httpSessionHelper == null){
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesHelper jamesCli(){
        if (jamesCliHelper == null){
            jamesCliHelper = new JamesHelper(this);
        }
        return jamesCliHelper;
    }

    public JamesApiHelper jamesApi(){
        if (jamesApiHelper == null){
            jamesApiHelper = new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }

    public MailHelper mail(){
        if (mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public String property(String name){
        return  properties.getProperty(name);

    }

        public UserHelper user(){
        if (userHelper == null){
            userHelper = new UserHelper(this);
        }
        return userHelper;
        }


    public RestApiHelper rest() {
        if (restApiHelper == null){
            restApiHelper = new RestApiHelper(this);
        }
        return restApiHelper;
    }

    public SoapApiHelper soap() {
        if (soapApiHelper == null){
            soapApiHelper = new SoapApiHelper(this);
        }
        return soapApiHelper;
    }
}
