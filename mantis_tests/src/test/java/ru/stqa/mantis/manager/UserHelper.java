package ru.stqa.mantis.manager;

import model.User;
import org.openqa.selenium.By;

public class UserHelper extends HelperBase{

    public UserHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }


    public void startRegisterUser(User user, String mail) {
        signupAccount();
        fillAccountForm(user,mail);
        submit();
    }

    public void finishRegisterUser(User user, String url){
        followUrl(url);
        fillEditForm(user);
        submitEditUser();

    }

    private void signupAccount(){
        click(By.xpath("//a[@class='back-to-login-link pull-left']"));

    }

    private void fillAccountForm(User user, String mail){
        type(By.name("username"), user.name());
        type(By.name("email"), mail);
    }

    private void fillEditForm(User user){
        type(By.name("realname"), user.name());
        type(By.name("password"), user.password());
        type(By.name("password_confirm"), user.password());

    }

    private void submit(){
        click(By.xpath("//input[@type='submit']"));
    }

    private void submitEditUser(){
        click(By.xpath("//button[@type='submit']"));
    }

    private void followUrl(String url){
        applicationManager.webDriver().navigate().to(url);
    }




}
