package manager;

import org.hibernate.Hibernate;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;


public class ApplicationManager {
    protected   WebDriver webDriver;
    private LoginHelper session;
    private GroupHelper group;
    private ContactHelper contact;
    private JdbcHelper jdbcHelper;
    private Properties properties;
    private HibernateHelper hbm;





    public void init(String browser, Properties properties) {
        this.properties = properties;

        String url = properties.getProperty("web.baseUrl");

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
            session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
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

    public ContactHelper contact(){
        if (contact == null){
            contact = new ContactHelper(this);
        }
        return contact;
    }

    public  JdbcHelper  jdbcHelper(){
        if (jdbcHelper == null){
            jdbcHelper = new JdbcHelper(this);
        }
        return jdbcHelper;
    }

    public HibernateHelper hmb(){
        if(hbm == null){
            hbm = new HibernateHelper(this);
        }
        return  hbm;
    }

}

