package ru.stqa.pft.addressbook.tests.groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupsPage();
    GroupData newGroup = new GroupData().withName("Relatives");
    if (app.group().all().size() == 0) app.group().create(newGroup);
  }

  @Test(testName = "Проверка удаления группы")
  public void testGroupDeletion() {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

  @Test(testName = "Проверка удаления всех групп")
  public void testAllGroupsDeletion() throws Exception {
    Groups before = app.group().all();
    app.group().deleteAll();
    assertThat(app.group().count(), equalTo(0));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.empty()));
  }
}