package ru.stqa.pft.addressbook.tests.groups;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[] {new GroupData().withName("group 1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[] {new GroupData().withName("group 2").withHeader("header 2").withFooter("footer 2")});
    list.add(new Object[] {new GroupData().withName("group 3").withHeader("header 3").withFooter("footer 3")});
    return list.iterator();
  }

  @Test(testName = "Проверка создания группы (параметризация)", dataProvider = "validGroups")
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