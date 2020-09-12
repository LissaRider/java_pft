package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupDeletionTests extends TestBase {

  public GroupData newGroup = new GroupData(
          "Relatives",
          null,
          null
  );

  @Test(testName = "Проверка удаления любой группы")
  public void testAnyGroupDeletion() {
    app.group().verifyGroupPresence(newGroup, 1);
    int before = app.group().getGroupsCount();
    app.group().removeAnyGroup();
    int after = app.group().getGroupsCount();
    Assert.assertEquals(after, before - 1);
  }

  @Test(testName = "Проверка удаления первой группы")
  public void testFirstGroupDeletion() {
    app.group().verifyGroupPresence(newGroup, 3);
    if (app.group().getGroupsCount() == 2)
      app.group().createGroups(newGroup, 1);
    int before = app.group().getGroupsCount();
    app.group().removeGroup(0);
    int after = app.group().getGroupsCount();
    Assert.assertEquals(after, before - 1);
  }

  @Test(testName = "Проверка удаления последней группы")
  public void testLastGroupDeletion() {
    app.group().verifyGroupPresence(newGroup, 3);
    if (app.group().getGroupsCount() == 2)
        app.group().createGroups(newGroup, 1);
    int before = app.group().getGroupsCount();
    app.group().removeGroup(before - 1);
    int after = app.group().getGroupsCount();
    Assert.assertEquals(after, before - 1);
  }
}