package ru.stqa.pft.addressbook.tests.groups.dbtests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationDBTests extends TestBase {

  @Test(testName = "Проверка создания группы (БД)")
  public void dBTestGroupCreation() {
    app.goTo().groupsPage();
    Groups before = app.db().groups();
    GroupData group = new GroupData()
            .withName("Relatives")
            .withHeader("<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>")
            .withFooter("<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>");
    app.group().create(group);
    assertThat(app.db().groups().size(), equalTo(before.size() + 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.withAdded(group.withId(after
            .stream()
            .mapToInt(GroupData::getId)
            .max()
            .orElseThrow())))); /*java 11: getAsInt() to orElseThrow()*/
    verifyGroupListInUI();
  }

  @Test(testName = "Проверка создания некорректной группы (БД)")
  public void dBTestBadGroupCreation() {
    app.goTo().groupsPage();
    Groups before = app.db().groups();
    GroupData group = new GroupData()
            .withName("Relatives'");
    app.group().create(group);
    assertThat(app.db().groups().size(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before));
    verifyGroupListInUI();
  }
}