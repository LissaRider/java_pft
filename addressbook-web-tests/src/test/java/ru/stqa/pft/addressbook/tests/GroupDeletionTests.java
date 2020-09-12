package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  public GroupData newGroup = new GroupData(
          "Relatives",
          null,
          null
  );

  @Test(testName = "Проверка удаления первой группы")
  public void testFirstGroupDeletion() {
    app.group().verifyGroupPresence(newGroup, 3);
    if (app.group().getGroupsCount() == 2)
      app.group().createGroups(newGroup, 1);
    List<GroupData> before = app.group().getGroupsList();
    app.group().removeGroup(0);
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(0);
    Assert.assertEquals(after, before);
  }

  @Test(testName = "Проверка удаления последней группы")
  public void testLastGroupDeletion() {
    app.group().verifyGroupPresence(newGroup, 3);
    if (app.group().getGroupsCount() == 2)
      app.group().createGroups(newGroup, 1);
    List<GroupData> before = app.group().getGroupsList();
    app.group().removeGroup(before.size() - 1);
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }
}