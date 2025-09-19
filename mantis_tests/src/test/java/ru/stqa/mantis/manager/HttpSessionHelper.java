package ru.stqa.mantis.manager;

import okhttp3.*;
import ru.stqa.mantis.tests.TestBase;

import java.io.IOException;
import java.net.CookieManager;

public class HttpSessionHelper extends HelperBase {

    OkHttpClient client;

    public HttpSessionHelper(ApplicationManager applicationManager) {
        super(applicationManager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void login(String user, String pass) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", user)
                .add("password", pass)
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/login.php", applicationManager.property("web.baseUrl")))
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn(){
        Request request = new Request.Builder()
                .url(applicationManager.property("web.baseUrl"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String body = response.body().string();
            return body.contains("<span class=\"user-info\">");
        } catch (IOException e) {
            throw new RuntimeException(e);
    }
}
}
