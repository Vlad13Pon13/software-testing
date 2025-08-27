package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    public void setUp(){
        if (app == null){
            app= new ApplicationManager();
        }
        app.init(System.getProperty("browser", "firefox"));

    }

    public static String randomString(int n){
        Random rnd = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i< n; i++){
            result.append((char) ('a' + rnd.nextInt(26)));
        }

        return result.toString();

    }

    public static String randomPhoneNumber(){
        Random rnd = new Random();
        int areaCodeNumber = rnd.nextInt(1000);
        String codeNumberStr = String.format("%03d", areaCodeNumber);

        int mainNumber = rnd.nextInt(10000000);
        String mainNumberStr = String.format("%07d", mainNumber);

        String fullNumber = "8("+codeNumberStr+")-"+mainNumberStr;
        return fullNumber;
    }

}
