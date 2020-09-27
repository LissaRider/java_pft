package ru.stqa.pft.addressbook.tests.groups;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[]{new GroupData().withName("group 1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[]{new GroupData().withName("group 2").withHeader("header 2").withFooter("footer 2")});
    list.add(new Object[]{new GroupData().withName("group 3").withHeader("header 3").withFooter("footer 3")});
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> groupsFromCsvFile() throws IOException {
    List<Object[]> list = new ArrayList<>();
    try (var reader = new BufferedReader(new FileReader(
            new File("src/test/resources/data/groups/groups.csv")))) {
      var line = reader.readLine();
      while (line != null) {
        String[] split = line.split(";");
        list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> groupsFromXmlFile() throws IOException {
    try (var reader = new BufferedReader(new FileReader(
            new File("src/test/resources/data/groups/groups.xml")))) {
      StringBuilder xml = new StringBuilder();
      var line = reader.readLine();
      while (line != null) {
        xml.append(line);
        line = reader.readLine();
      }
      var xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      //noinspection unchecked
      var groups = (List<GroupData>) xstream.fromXML(xml.toString());
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> groupsFromJsonFile() throws IOException {
    try (var reader = new BufferedReader(new FileReader(
            new File("src/test/resources/data/groups/groups.json")))) {
      var json = new StringBuilder();
      String line = reader.readLine();
      while (line != null) {
        json.append(line);
        line = reader.readLine();
      }
      var gson = new Gson();
      List<GroupData> groups = gson.fromJson(json.toString(), new TypeToken<List<GroupData>>() { }.getType());
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> groupsFromYamlFile() throws IOException {
    var mapper = new ObjectMapper(new YAMLFactory()); // AutoCloseable
    List<GroupData> groups = mapper.readValue(
            new File("src/test/resources/data/groups/groups.yaml"), new TypeReference<>() { });
    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

//  @Test(testName = "Проверка создания группы (параметризация)", dataProvider = "validGroups")
//  @Test(testName = "Проверка создания группы (чтение данных в формате CSV)", dataProvider = "groupsFromCsvFile")
//  @Test(testName = "Проверка создания группы (чтение данных в формате XML)", dataProvider = "groupsFromXmlFile")
//  @Test(testName = "Проверка создания группы (чтение данных в формате JSON)", dataProvider = "groupsFromJsonFile")
  @Test(testName = "Проверка создания группы (чтение данных в формате YAML)", dataProvider = "groupsFromYamlFile")
  public void parameterizedTestGroupCreation(GroupData group) {
    app.goTo().groupsPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded(group.withId(after
            .stream()
            .mapToInt(GroupData::getId)
            .max()
            .orElseThrow())))); /*java 11: getAsInt() to orElseThrow()*/
  }

  @Test(testName = "Проверка создания группы")
  public void testGroupCreation() {
    app.goTo().groupsPage();
    Groups before = app.group().all();
    GroupData group = new GroupData()
            .withName("Relatives")
            .withHeader("<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>")
            .withFooter("<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded(group.withId(after
            .stream()
            .mapToInt(GroupData::getId)
            .max()
            .orElseThrow())))); /*java 11: getAsInt() to orElseThrow()*/
  }

  @Test(testName = "Проверка создания некорректной группы")
  public void testBadGroupCreation() {
    app.goTo().groupsPage();
    Groups before = app.group().all();
    GroupData group = new GroupData()
            .withName("Relatives'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}