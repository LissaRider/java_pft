package ru.stqa.pft.mantis.tests;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws IOException {
    app.init();
    // в последней версии mantis config_inc.php находится в папке config
    app.ftp().upload(new File("src/test/resources/config/config_inc.php"),
            "config/config_inc.php", "config/config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
    app.stop();
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) throw new SkipException("Ignored because of issue " + issueId);
  }

  boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    var issue = app.soap().getIssueById(issueId);
    System.out.println("==================================== ISSUE =======================================");
    System.out.println(issue);
    var status = issue.getStatus();
    var resolution = issue.getResolution();
    var closedStatuses = new ArrayList<>(Arrays.asList("resolved", "closed"));
    return !closedStatuses.contains(status) && !resolution.equals("fixed");
  }
}