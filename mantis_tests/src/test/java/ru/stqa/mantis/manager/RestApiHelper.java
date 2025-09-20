package ru.stqa.mantis.manager;

import coomon.CommonFunctions;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.*;
import model.IssueData;
import model.UserForApi;

public class RestApiHelper extends HelperBase{
    public RestApiHelper(ApplicationManager applicationManager) {
        super(applicationManager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(applicationManager.property("apiKey"));
    }


    public void createIssue(IssueData issueData) {

        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            new RuntimeException(e);
        }

    }

    public void registrationUser(UserForApi user){
        User body = new User();
        body.setUsername(user.userName());
        body.setRealName(user.realName());
        body.setPassword(user.password());
        body.setEmail(user.email());
        body.setEnabled(user.enabled());
        body.setProtected(user.protect());
        var access = new AccessLevel();
        access.setId(user.accessLevel());
        body.setAccessLevel(access);

        UserApi startRegistrationUser = new UserApi();
        try {
            var response = startRegistrationUser.userAddWithHttpInfo(body);
            System.out.println(response + "");
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }


    }
}
