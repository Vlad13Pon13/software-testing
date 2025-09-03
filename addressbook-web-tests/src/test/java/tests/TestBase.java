package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    protected static ApplicationManager app;
    public static ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setUp(){
        if (app == null){
            app= new ApplicationManager();
        }
        app.init(System.getProperty("browser", "firefox"));

    }


}
