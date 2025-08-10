import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class RemoveGroupsTest {

    public static WebDriver webDriver;
    final String url = "http://localhost/addressbook/";

    @BeforeEach
    public void setUp(){
        if (webDriver == null){
            webDriver = new FirefoxDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(webDriver::quit)); //дополнительно изучить для понимания
            //Авторизация
            webDriver.get(url);
            webDriver.manage().window().fullscreen();
            webDriver.findElement(By.name("user")).sendKeys("admin");
            webDriver.findElement(By.name("pass")).sendKeys("secret");
            webDriver.findElement(By.xpath("//input[@value='Login']")).click();
        }

    }

    @Test()
    @DisplayName("Тест удаления всех групп контактов")
    public void removeGroup() throws InterruptedException {
        if (!isElementPresent(By.name("new"))) {
            webDriver.findElement(By.linkText("groups")).click();
        }
        if (!isElementPresent(By.name("selected[]"))){
            webDriver.findElement(By.name("new")).click();
            webDriver.findElement(By.name("group_name")).click();
            webDriver.findElement(By.name("group_name")).sendKeys("FirstGroup");
            webDriver.findElement(By.name("group_header")).sendKeys("header");
            webDriver.findElement(By.name("group_footer")).sendKeys("comment");
            webDriver.findElement(By.name("submit")).click();
            webDriver.findElement(By.linkText("group page")).click();
        }

        //отмечаем все чек-боксы, если создано несколько тестовых групп
        List<WebElement> checkboxes = webDriver.findElements(By.name("selected[]"));
        for (WebElement checkbox : checkboxes){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }
        webDriver.findElement(By.name("delete")).click();
        webDriver.findElement(By.linkText("group page")).click();



    }

    private boolean isElementPresent(By locator) {
        try {
            webDriver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }

    }



}
