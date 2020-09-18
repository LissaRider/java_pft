package ru.stqa.pft.addressbook.tests.groups;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  public GroupData newGroup = new GroupData("Relatives");

  @BeforeMethod
  public void ensurePreconditions() {
    app.group().verifyGroupPresence(newGroup, 3);
  }

  @Test(testName = "Проверка удаления первой группы")
  public void testFirstGroupDeletion() {
    List<GroupData> before = app.group().getGroupsList();
    app.group().removeGroup(0);
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(0);
    Assert.assertEquals(after, before);
  }

  @Test(testName = "Проверка удаления последней группы")
  public void testLastGroupDeletion() {
    List<GroupData> before = app.group().getGroupsList();
    app.group().removeGroup(before.size() - 1);
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }
}