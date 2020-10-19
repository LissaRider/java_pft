package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.apache.axis.SimpleChain;
import org.apache.axis.SimpleTargetedChain;
import org.apache.axis.client.AxisClient;
import org.apache.axis.configuration.SimpleProvider;
import org.apache.axis.transport.http.HTTPSender;
import org.apache.axis.transport.http.HTTPTransport;
import ru.stqa.pft.mantis.models.Issue;
import ru.stqa.pft.mantis.models.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws ServiceException, MalformedURLException, RemoteException {
    var mc = getMantisConnect();
    var projects = mc.mc_projects_get_user_accessible("administrator", "root");
    return Arrays.stream(projects)
            .map(p -> new Project()
                    .withId(p.getId().intValue())
                    .withName(p.getName()))
            .collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    var clientConfig = new SimpleProvider();
    var logHandler = new AxisLogHandler();
    var reqHandler = new SimpleChain();
    var respHandler = new SimpleChain();
    reqHandler.addHandler(logHandler);
    respHandler.addHandler(logHandler);
    var pivot = new HTTPSender();
    var transport = new SimpleTargetedChain(reqHandler, pivot, respHandler);
    clientConfig.deployTransport(HTTPTransport.DEFAULT_TRANSPORT_NAME, transport);

    var locator = new MantisConnectLocator();
    locator.setEngineConfiguration(clientConfig);
    locator.setEngine(new AxisClient(clientConfig));
    return locator.getMantisConnectPort(new URL("http://localhost/mantisbt-2.24.2/api/soap/mantisconnect.php"));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    var mc = getMantisConnect();

    var issueProject = issue.getProject();

    var categories = mc.mc_project_get_categories(
            "administrator",
            "root",
            BigInteger.valueOf(issueProject.getId()));

    var issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());

    var project = new ObjectRef(
            BigInteger.valueOf(issueProject.getId()),
            issueProject.getName());

    issueData.setProject(project);

    issueData.setCategory(categories[0]);

    var issueId = mc.mc_issue_add("administrator", "root", issueData);

    var createdIssueData = mc.mc_issue_get("administrator", "root", issueId);

    var createdIssueDataProject = createdIssueData.getProject();
    return new Issue()
            .withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project()
                    .withId(createdIssueDataProject.getId().intValue())
                    .withName(createdIssueDataProject.getName()));
  }
}