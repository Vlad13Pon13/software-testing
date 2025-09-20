package manager;

import io.qameta.allure.Step;
import models.ContactData;
import models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {


    public ContactHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void openHomePage() {
         {
            click(By.linkText("home"));
        }
    }

    @Step
    public void createNewContact(ContactData data) {
        openHomePage();
        initContactCreation();
        fillContractForm(data);
        submitContactCreation();
        returnHomePage();

    }

    public void createContactInGroup(ContactData contact, GroupData group) {
        openHomePage();
        initContactCreation();
        fillContractForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnHomePage();

    }

    private void selectGroup(GroupData group) {
        new Select( applicationManager.webDriver.findElement(By.name("new_group"))).selectByValue(group.id());
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

    @Step
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

    @Step
    private void removeContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public int countContact() {
        openHomePage();
        return applicationManager.webDriver.findElements(By.name("selected[]")).size();
    };

    @Step

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

    @Step
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

    @Step
    public void removeContact() {
        openHomePage();
        selectContact();
        removeContacts();
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    public void addContactInGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContactForAdd(contact);
        addGroup(group);
        returnHomePage();
    }

    private void addGroup(GroupData group) {
        new Select( applicationManager.webDriver.findElement(By.name("to_group"))).selectByValue(group.id());
        click(By.name("add"));
    }

    private void selectContactForAdd(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.getId())));
    }

    @Step
    public void removeContactInGroup(GroupData group, ContactData contact) {
        openHomePage();
        selectGroupWithContacts(group);
        selectContactInGroup(contact);
        removeOneContactInGroup();
        returnGroupPage(group);
    }

    private void selectContactInGroup(ContactData contact) {
        click(By.xpath(String.format("//input[@type='checkbox' and @value='%s']",contact.getId())));
    }

    private void returnGroupPage(GroupData group) {
        click(By.xpath(String.format("//a[@href='./?group=%s']",group.id())));

    }

    private void removeOneContactInGroup() {
        click(By.name("remove"));
    }


    private void selectGroupWithContacts(GroupData group) {
        new Select( applicationManager.webDriver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public String getPhones(ContactData contact) {
        openHomePage();
        return applicationManager.webDriver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]",contact.getId()))).getText();
    }

    public Map<String,String> getPhones() {
        var result = new HashMap<String,String>();
        var rows = applicationManager.webDriver.findElements(By.name("entry"));
        for (var row: rows){
            var idContact = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(idContact, phones);

        }
        return result;
    }

    public Map<String,String> getEmails() {
        var result = new HashMap<String,String>();
        var rows = applicationManager.webDriver.findElements(By.name("entry"));
        for (var row: rows){
            var idContact = row.findElement(By.tagName("input")).getAttribute("id");
            var mails = row.findElements(By.tagName("td")).get(4).getText();
            result.put(idContact, mails);

        }
        return result;
    }

    public Map<String,String> getAddress() {
        var result = new HashMap<String,String>();
        var rows = applicationManager.webDriver.findElements(By.name("entry"));
        for (var row: rows){
            var idContact = row.findElement(By.tagName("input")).getAttribute("id");
            var address = row.findElements(By.tagName("td")).get(3).getText();
            result.put(idContact, address);

        }
        return result;
    }
}
