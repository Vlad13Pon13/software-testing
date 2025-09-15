package ru.stqa.mantis.tests;

import coomon.CommonFunctions;
import org.junit.jupiter.api.Test;

public class JamesTest extends TestBase{

    @Test
    public void createUser(){
        app.jamesCli().addUser(
                String.format("%s@localhost", CommonFunctions.randomString(4)), "password");
    }
}
