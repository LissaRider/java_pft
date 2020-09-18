package ru.stqa.pft.addressbook.tests.groups;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    GroupData newGroup = new GroupData("Relatives");
    app.group().verifyPresence(newGroup, 2);
  }

  @Test(testName = "Проверка удаления первой группы")
  public void testFirstGroupDeletion() {
    List<GroupData> before = app.group().list();
    app.group().delete(0);
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(0);
    Assert.assertEquals(after, before);
  }

  @Test(testName = "Проверка удаления последней группы")
  public void testLastGroupDeletion() {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), index);
    before.remove(index);
    Assert.assertEquals(after, before);
  }
}