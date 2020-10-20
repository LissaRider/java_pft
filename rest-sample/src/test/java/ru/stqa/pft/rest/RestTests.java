package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests {

  @Test
  public void testCreateIssue() throws IOException {
    var oldIssues = getIssues();
    var newIssue = new Issue()
            .withSubject("Test issue")
            .withDescription("New test issue");
    int issueId = createIssue(newIssue);
    var newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  private Set<Issue> getIssues() throws IOException {
    var url = "https://bugify.stqa.ru/api";
    var json = getExecutor().execute(Request.Get(url + "/issues.json")).returnContent().asString();
    var parsed = JsonParser.parseString(json);
    var jsonObject = parsed.getAsJsonObject();
    var issues = jsonObject.get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() { }.getType());
  }

  private Executor getExecutor() {
    var key = "288f44776e7bec4bf44fdfeb1e646490";
    return Executor.newInstance().auth(key, "");
  }

  private int createIssue(Issue newIssue) throws IOException {
    var url = "https://bugify.stqa.ru/api";
    var json = getExecutor()
            .execute(Request.Post(url + "/issues.json").bodyForm(
                    new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    System.out.println(json);
    var parsed = JsonParser.parseString(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }
}