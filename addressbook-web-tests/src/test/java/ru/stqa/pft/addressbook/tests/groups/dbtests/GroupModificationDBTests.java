package ru.stqa.pft.addressbook.tests.groups.dbtests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationDBTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupsPage();
    GroupData newGroup = new GroupData()
            .withName("Relatives")
            .withHeader("<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>")
            .withFooter("<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>");
    if (app.db().groups().size() == 0) app.group().create(newGroup);
  }

  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("Friends")
            .withHeader("<h1>FRIENDS</h1><p>Created by Lissa Rider</p></p>")
            .withFooter("<a href=\"index.php\">home</a>");
    app.group().modify(group);
    assertThat(app.db().groups().size(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }
}