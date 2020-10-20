package ru.stqa.pft.rest.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.models.Issue;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class RestAssTests extends TestBase {

  @BeforeClass
  public void init() {
    RestAssured.authentication = app.restAss().auth();
  }

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixedRA(3);
    var oldIssues = app.restAss().getIssues();
    var newIssue = new Issue()
            .withSubject("Test issue")
            .withDescription("New test issue");
    int issueId = app.restAss().createIssue(newIssue);
    var newIssues = app.restAss().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testGetIssues() throws IOException {
    skipIfNotFixedRA(323);
    var issues = app.restAss().getIssues();
    System.out.println("==================================== TEST DATA =======================================");
    System.out.println("Issues number: " + issues.size());
    if (!issues.isEmpty()) {
      System.out.println("Issues:");
      for (var issue : issues) {
        System.out.println("  {\"id\":" + issue.getId()
                + ",\"subject\":\"" + issue.getSubject()
                + "\",\"description\":\"" + issue.getDescription()
                + "\",\"state_name\":\"" + issue.getStatus() + "\"}");
      }
    }
  }
}