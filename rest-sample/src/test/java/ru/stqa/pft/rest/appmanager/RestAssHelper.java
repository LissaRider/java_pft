package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import org.apache.http.client.fluent.Request;
import ru.stqa.pft.rest.models.Issue;

import java.io.IOException;
import java.util.Set;

public class RestAssHelper {
  private final ApplicationManager app;

  public RestAssHelper(ApplicationManager app) {
    this.app = app;
  }

  public AuthenticationScheme auth() {
    var key = app.getProperty("api.key");
    return RestAssured.basic(key, "");
  }

  public Set<Issue> getIssues() {
    var url = app.getProperty("api.url");
    var json = RestAssured.get(url + "/issues.json").asString();
    var parsed = JsonParser.parseString(json);
    var jsonObject = parsed.getAsJsonObject();
    var issues = jsonObject.get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  public int createIssue(Issue newIssue) {
    var url = app.getProperty("api.url");
    var json = RestAssured.given().param("subject", newIssue.getSubject())
            .param("description", newIssue.getDescription())
            .post(url + "/issues.json").asString();
    System.out.println(json);
    var parsed = JsonParser.parseString(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public Set<Issue> getIssueById(int issueId) throws IOException {
    var url = app.getProperty("api.url");
    var json = RestAssured.get(url + "/issues/" + issueId + ".json").asString();
    var parsed = JsonParser.parseString(json);
    System.out.println("=================================== RESPONSE ======================================");
    System.out.println(parsed);
    var issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() { }.getType());
  }
}