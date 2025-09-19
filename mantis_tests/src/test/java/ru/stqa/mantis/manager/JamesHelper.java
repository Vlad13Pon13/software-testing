package ru.stqa.mantis.manager;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.CommandLine;
import org.openqa.selenium.os.ExternalProcess;
import ru.stqa.mantis.tests.TestBase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JamesHelper extends HelperBase {
    public JamesHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void addUser(String email, String pass){
        CommandLine cmd = new CommandLine(
                "java",
                "-cp",
                "\"james-server-jpa-app.lib/*\"",
                "org.apache.james.cli.ServerCmd",
                "AddUser", email, pass);
        cmd.setWorkingDirectory(applicationManager.property("james.workingDir"));
        CircularOutputStream out = new CircularOutputStream();
        cmd.copyOutputTo(out);
        cmd.execute();
        cmd.waitFor();
        System.out.println(out);

    }

    public void removeUsers(String email){
        CommandLine cmd = new CommandLine(
                "java",
                "-cp",
                "\"james-server-jpa-app.lib/*\"",
                "org.apache.james.cli.ServerCmd",
                "RemoveUser", email);
        cmd.setWorkingDirectory(applicationManager.property("james.workingDir"));
        CircularOutputStream out = new CircularOutputStream();
        cmd.copyOutputTo(out);
        cmd.execute();
        cmd.waitFor();
        System.out.println(out);

    }


}
