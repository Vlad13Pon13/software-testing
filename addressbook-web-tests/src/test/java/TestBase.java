import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    public void setUp(){
        if (app == null){
            app= new ApplicationManager();
        }
        app.init();

    }

}
