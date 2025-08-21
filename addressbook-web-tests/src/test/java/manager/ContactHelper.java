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
        initCotractCreation();
        fillContractForm(data);
        submitContactCreation();
        returnHomePage();



    }

    private void fillContractForm(ContactData data){
        type(By.name("firstname"),data.firstName);
        type(By.name("middlename"),data.middleName);
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
        typeDate(By.name("bday"),By.xpath(String.format("//option[. = '%s']", data.birthDay)));
        typeDate(By.name("bmonth"),By.xpath(String.format("//option[. = '%s']", data.birthMonth)));
        type(By.name("byear"), data.birthYear);
        typeDate(By.name("aday"),By.xpath(String.format("//option[. = '%s']", data.anniversaryDay)));
        typeDate(By.name("amonth"),By.xpath(String.format("//option[. = '%s']", data.anniversaryMonth)));
        type(By.name("ayear"), data.anniversaryYear);

    }

    private  void initCotractCreation() {
        click(By.linkText("add new"));

    }
    private void submitContactCreation(){
        click(By.name("submit"));
    }

    private void returnHomePage(){
        click(By.linkText("home"));
    }


}
