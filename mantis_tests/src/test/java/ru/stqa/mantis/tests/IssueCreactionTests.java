package ru.stqa.mantis.tests;

import coomon.CommonFunctions;
import model.IssueData;
import org.junit.jupiter.api.Test;

public class IssueCreactionTests extends TestBase{

    @Test
    public void canCreateIssue(){
        app.rest().createIssue(new IssueData()
                .withSummary(CommonFunctions.randomString(6))
                .withDescription(CommonFunctions.randomString(30))
                .withProject(1L)

        );

    }

}
