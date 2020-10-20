package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;


public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  boolean isIssueOpen(int issueId) throws IOException {
    var issue = app.rest().getIssueById(issueId).iterator().next();
    System.out.println("==================================== ISSUE =======================================");
    System.out.println("{\"id\":" +issue.getId()
            + ",\"subject\":\""+ issue.getSubject()
            + "\",\"description\":\""+issue.getDescription()
            +"\",\"state_name\":\""+issue.getStatus()+"\"}");
    return issue.getStatus().equals("Open");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) throw new SkipException("Ignored because of issue " + issueId);
  }

  boolean isIssueOpenRA(int issueId) throws IOException {
    var issue = app.restAss().getIssueById(issueId).iterator().next();
    System.out.println("==================================== ISSUE =======================================");
    System.out.println("{\"id\":" +issue.getId()
            + ",\"subject\":\""+ issue.getSubject()
            + "\",\"description\":\""+issue.getDescription()
            +"\",\"state_name\":\""+issue.getStatus()+"\"}");
    return issue.getStatus().equals("Open");
  }

  public void skipIfNotFixedRA(int issueId) throws IOException {
    if (isIssueOpen(issueId)) throw new SkipException("Ignored because of issue " + issueId);
  }
}
