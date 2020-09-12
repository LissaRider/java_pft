package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

  public GroupData newGroup = new GroupData(
          "Relatives",
          null,
          null
  );

  @Test(testName = "Проверка редактирования группы")
  public void testAnyGroupModification() {
    app.group().verifyGroupPresence(newGroup, 1);
    List<GroupData> before = app.group().getGroupsList();
    app.group().modifyAnyGroup(new GroupData(
            "Friends",
            "<h1>FRIENDS</h1><p>Created by Lissa Rider</p></p>",
            "<a href=\"index.php\">home</a>"
    ));
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size());
  }

  @Test(testName = "Проверка редактирования группы (с неизменяющимися значениями)")
  public void testGroupModificationWithSameValues() {
    app.group().verifyGroupPresence(newGroup, 1);
    List<GroupData> before = app.group().getGroupsList();
    app.group().modifyAnyGroup(new GroupData(
            "Relatives",
            "<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>",
            "<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>"
    ));
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size());
  }
}