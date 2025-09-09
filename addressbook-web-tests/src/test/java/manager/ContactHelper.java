package manager;

import models.ContactData;
import models.GroupData;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {


    public ContactHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void openHomePage() {
        if (!applicationManager.contact().applicationManager.isElementPresent(By.xpath("//input[@value='Send e-Mail']"))) {
            click(By.linkText("home"));
        }
    }

    public void createNewContract(ContactData data) {
        openHomePage();
        initContactCreation();
        fillContractForm(data);
        submitContactCreation();
        returnHomePage();

    }

    private void fillContractForm(ContactData data) {
        type(By.name("firstname"), data.firstName);
        type(By.name("middlename"), data.middleName);
        type(By.name("lastname"), data.lastName);
        type(By.name("nickname"), data.nickName);
        if (data.photo != null && !data.photo.trim().isEmpty()) {
            attach(By.name("photo"), data.photo);
        }
        type(By.name("title"), data.title);
        type(By.name("company"), data.company);
        type(By.name("address"), data.address);
        type(By.name("home"), data.home);
        type(By.name("mobile"), data.mobile);
        type(By.name("work"), data.work);
        type(By.name("fax"), data.fax);
        type(By.name("email"), data.mail);
        type(By.name("email2"), data.mailTwo);
        type(By.name("email3"), data.mailThree);
        type(By.name("homepage"), data.homepage);
        selectElementDate(By.name("bday"), data.birthDay);
        selectElementDate(By.name("bmonth"), data.birthMonth);
        type(By.name("byear"), data.birthYear);
        selectElementDate(By.name("aday"), data.anniversaryDay);
        selectElementDate(By.name("amonth"), data.anniversaryMonth);
        type(By.name("ayear"), data.anniversaryYear);

    }

    public void removeAllContact() {
        openHomePage();
        selectAllForDelete();
        removeContacts();

    }

    private void initContactCreation() {
        click(By.linkText("add new"));

    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnHomePage() {
        click(By.linkText("home"));
    }

    public boolean isContactPresent() {
        openHomePage();
        return applicationManager.isElementPresent(By.name("selected[]"));
    }

    private void selectAllForDelete() {
        click(By.xpath("//input[@id='MassCB']"));
    }

    private void removeContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public int countContact() {
        openHomePage();
        return applicationManager.webDriver.findElements(By.name("selected[]")).size();
    }

    ;

    public void modifyContact(ContactData contact, ContactData data) {
        openHomePage();
        selectContactForModify(contact);
        fillContractForm(data);
        acceptChange();
        returnHomePageAfterUpdate();

    }

    private void returnHomePageAfterUpdate() {
        click(By.linkText("home page"));
    }

    private void acceptChange() {
        click(By.name("update"));
    }

    private void selectContactForModify(ContactData contact) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.getId())));
    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        List<WebElement> tds = applicationManager.webDriver.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement td : tds) {
            var checkbox = td.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            var column = td.findElements(By.tagName("td"));
            var lastName = column.get(1).getText();
            var name = column.get(2).getText();
            var address = column.get(3).getText();
            var mail = column.get(4).getText();
            var mobile = column.get(5).getText();
            var contact = new ContactData(name,  lastName, address, mobile, mail);
            contact.setId(id);
            contacts.add(contact);

        }

        return contacts;


    }

    public void removeContact() {
        openHomePage();
        selectContact();
        removeContacts();
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

}
