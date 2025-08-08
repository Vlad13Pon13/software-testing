
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;


public class FirstSelenuimTest {
  private WebDriver driver;
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;

  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void test() throws InterruptedException {

    String url = "http://localhost/addressbook/";

    driver.get(url);
    driver.manage().window().setSize(new Dimension(1936, 1048));
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    driver.findElement(By.xpath("//*[@id=\"top\"]/form/a")).click();
    
  }


}