package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests {

  @BeforeClass
  public void init() {
    var key = "288f44776e7bec4bf44fdfeb1e646490";
    RestAssured.authentication = RestAssured.basic(key, "");
  }

  @Test
  public void testCreateIssue() {
    var oldIssues = getIssues();
    var newIssue = new Issue()
            .withSubject("Test issue")
            .withDescription("New test issue");
    int issueId = createIssue(newIssue);
    var newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  private Set<Issue> getIssues() {
    var url = "https://bugify.stqa.ru/api";
    var json = RestAssured.get(url + "/issues.json").asString();
    var parsed = JsonParser.parseString(json);
    var jsonObject = parsed.getAsJsonObject();
    var issues = jsonObject.get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  private int createIssue(Issue newIssue) {
    var url = "https://bugify.stqa.ru/api";
    var json = RestAssured.given().param("subject", newIssue.getSubject())
            .param("description", newIssue.getDescription())
            .post(url + "/issues.json").asString();
    System.out.println(json);
    var parsed = JsonParser.parseString(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }
}