package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.models.Issue;

import java.io.IOException;
import java.util.Set;

public class RestHelper {

  private final ApplicationManager app;

  public RestHelper(ApplicationManager app) {
    this.app = app;
  }

  public Executor getExecutor() {
    var key = app.getProperty("api.key");
    return Executor.newInstance().auth(key, "");
  }

  public Set<Issue> getIssues() throws IOException {
    var url = app.getProperty("api.url");
    var json = getExecutor().execute(Request.Get(url + "/issues.json")).returnContent().asString();
    var parsed = JsonParser.parseString(json);
    var jsonObject = parsed.getAsJsonObject();
    var issues = jsonObject.get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() { }.getType());
  }

  public int createIssue(Issue newIssue) throws IOException {
    var url = app.getProperty("api.url");
    var json = getExecutor()
            .execute(Request.Post(url + "/issues.json").bodyForm(
                    new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    System.out.println("=================================== RESPONSE ======================================");
    System.out.println(json);
    var parsed = JsonParser.parseString(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public Set<Issue> getIssueById(int issueId) throws IOException {
    var url = app.getProperty("api.url");
    var json = getExecutor().execute(Request
            .Get(url + "/issues/" + issueId + ".json"))
            .returnContent()
            .asString();
    var parsed = JsonParser.parseString(json);
    System.out.println("=================================== RESPONSE ======================================");
    System.out.println(parsed);
    var issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() { }.getType());
  }
}