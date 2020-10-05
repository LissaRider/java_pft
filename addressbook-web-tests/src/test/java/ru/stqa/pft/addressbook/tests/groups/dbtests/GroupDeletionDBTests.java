package ru.stqa.pft.addressbook.tests.groups.dbtests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionDBTests extends TestBase {

  @BeforeMethod
  public void ensureDBPreconditions() {
    app.goTo().groupsPage();
    GroupData newGroup = new GroupData().withName("Relatives");
    if (app.db().groups().size() == 0) app.group().create(newGroup);
  }

  @Test(testName = "Проверка удаления группы (БД)")
  public void dBTestGroupDeletion() {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.db().groups().size(), equalTo(before.size() - 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListInUI();
  }

  @Test(testName = "Проверка удаления всех групп (БД)")
  public void dBTestAllGroupsDeletion() throws Exception {
    Groups before = app.db().groups();
    app.group().deleteAll();
    assertThat(app.db().groups().size(), equalTo(0));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.empty()));
    verifyGroupListInUI();
  }
}