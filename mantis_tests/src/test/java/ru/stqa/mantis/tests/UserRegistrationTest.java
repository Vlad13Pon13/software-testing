package ru.stqa.mantis.tests;

import coomon.CommonFunctions;
import model.User;
import model.UserForApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

public class UserRegistrationTest extends TestBase{


    @ParameterizedTest
    @MethodSource("randomUser")
    public void canRegisterUser(User user) {
       var mail = String.format("%s@localhost",user.name());
       //Создали почту + старт регистрации аккаунта
       app.jamesCli().addUser(mail, user.password());
       app.user().startRegisterUser(user, mail);

       //получили url из письма и после почистили ящик
       var messageMail = app.mail().receive(mail, user.password(), Duration.ofSeconds(10));
       var url = app.mail().extractUrl(messageMail);
       app.mail().drain(mail, user.password());

       //завершаем регистрацию пользователя
       app.user().finishRegisterUser(user, url);

       //проверяем через http protocol
       app.http().login(user.name(), user.password());
       Assertions.assertTrue(app.http().isLoggedIn());

       //удаляем почту с клиента c сервера
        app.jamesCli().removeUsers(mail);

    }

    @ParameterizedTest
    @MethodSource("randomUserApi")
    public void canRegisterUserApi(UserForApi user) {

        //1.Создать почту
        app.jamesApi().addUser(user.email(), user.password());
        app.rest().registrationUser(user);

        var  messageMail = app.mail().receive(user.email(), user.password(), Duration.ofSeconds(10));
        var url = app.mail().extractUrl(messageMail);
        app.mail().drain(user.email(), user.password());


        //завершаем регистрацию пользователя
        User editUser = new User(user.userName());
        app.user().finishRegisterUser(editUser, url);

        //проверяем через http protocol
        app.http().login(user.userName(), user.password());
        Assertions.assertTrue(app.http().isLoggedIn());



    }




    static Stream<UserForApi> randomUserApi(){
        return Stream.of(new UserForApi()
                .withName(CommonFunctions.randomString(5))
                .withEmail(String.format("%s@localhost",CommonFunctions.randomString(5)))
                .withRealName(CommonFunctions.randomString(5)));

    }


    static Stream<User> randomUser(){
        return Stream.of(new User(CommonFunctions.randomString(4)));

    }

}
