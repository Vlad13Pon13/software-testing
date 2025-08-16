package manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase{


    public LoginHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }


    public void login(String user, String password) {
        type(By.name("user"),user);
        type(By.name("pass"),password);
        click(By.xpath("//input[@value='Login']"));
    }

}
