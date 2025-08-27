package manager;

import models.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase{


    public ContactHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void openHomePage(){
        if (!applicationManager.contact().applicationManager.isElementPresent(By.xpath("//input[@value='Send e-Mail']"))){
            click(By.linkText("home"));
        }
    }

    public void createNewContract(ContactData data){
        openHomePage();
        initContactCreation();
        fillContractForm(data);
        submitContactCreation();
        returnHomePage();

    }

    private void fillContractForm(ContactData data){
        type(By.name("firstname"),data.firstName);
        type(By.name("middlename"),data.middleName);
        type(By.name("lastname"),data.lastName);
        type(By.name("nickname"),data.nickName);
        type(By.name("title"), data.title);
        type(By.name("company"),data.company);
        type(By.name("address"),data.address);
        type(By.name("home"),data.home);
        type(By.name("mobile"),data.mobile);
        type(By.name("work"),data.work);
        type(By.name("fax"), data.fax);
        type(By.name("email"),data.mail);
        type(By.name("email2"),data.mailTwo);
        type(By.name("email3"),data.mailThree);
        type(By.name("homepage"),data.homepage);
        selectElementDate(By.name("bday"),data.birthDay);
        selectElementDate(By.name("bmonth"), data.birthMonth);
        type(By.name("byear"), data.birthYear);
        selectElementDate(By.name("aday"), data.anniversaryDay);
        selectElementDate(By.name("amonth"), data.anniversaryMonth);
        type(By.name("ayear"), data.anniversaryYear);

    }

    public void removeAllContact(){
        openHomePage();
        selectAllForDelete();
        removeContacts();

    }

    private  void initContactCreation() {
        click(By.linkText("add new"));

    }
    private void submitContactCreation(){
        click(By.name("submit"));
    }

    private void returnHomePage(){
        click(By.linkText("home"));
    }

    public boolean isContactPresent(){
        openHomePage();
        return applicationManager.isElementPresent(By.name("selected[]"));
    }

    private void selectAllForDelete(){
        click(By.xpath("//input[@id='MassCB']"));
    }

    private void removeContacts(){
        click(By.xpath("//input[@value='Delete']"));
    }

    public int countContact(){
        openHomePage();
        return applicationManager.webDriver.findElements(By.name("selected[]")).size();
    };


}
