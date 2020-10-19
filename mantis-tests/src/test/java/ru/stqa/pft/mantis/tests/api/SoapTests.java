package ru.stqa.pft.mantis.tests.api;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.models.Issue;
import ru.stqa.pft.mantis.tests.TestBase;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

  @Test
  public void testGetProjects() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(3);
    var projects = app.soap().getProjects();
    System.out.println("==================================== TEST DATA =======================================");
    System.out.println("Projects number: " + projects.size());
    if(!projects.isEmpty())
    {
      System.out.println("Projects:");
      for (var project : projects) {
        System.out.println("  - " + project);
      }
    }
  }

  @Test
  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
    var projects = app.soap().getProjects();
    var issue = new Issue()
            .withSummary("Test Soap Issue")
            .withDescription("Test Soap Issue description")
            .withProject(projects.iterator().next());
    var created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }
}