package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.models.Issue;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(3);
    var oldIssues = app.rest().getIssues();
    var newIssue = new Issue()
            .withSubject("Test issue")
            .withDescription("New test issue");
    int issueId = app.rest().createIssue(newIssue);
    var newIssues = app.rest().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testGetIssues() throws IOException {
    skipIfNotFixed(323);
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