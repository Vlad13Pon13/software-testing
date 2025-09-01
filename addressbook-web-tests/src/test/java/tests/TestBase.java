package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Paths;
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

    public static String generateRandomName(String baseName) {
        Random rnd = new Random();
        StringBuilder result = new StringBuilder(baseName);
        for (int i = 0; i < 3; i++) {
            int randNum = rnd.nextInt(10); // число от 0 до 9
            result.append(randNum);
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

    public static String randomPhoto(String path){
        var file = new File(path).list();
        var rnd = new Random();
        Assertions.assertNotNull(file);
        var index = rnd.nextInt(file.length);
        return Paths.get(path, file[index]).toString();



    }


}
