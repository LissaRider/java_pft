package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  public GroupData newGroup = new GroupData(
          "Relatives",
          null,
          null
  );

  @Test(testName = "Проверка редактирования первой группы")
  public void testAnyGroupModification() {
    app.group().verifyGroupPresence(newGroup, 1);
    List<GroupData> before = app.group().getGroupsList();
    GroupData group = new GroupData(before.get(0).getId(),
            "Friends",
            "<h1>FRIENDS</h1><p>Created by Lissa Rider</p></p>",
            "<a href=\"index.php\">home</a>"
    );
    app.group().modifyGroup(0, group);
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(0);
    before.add(group);
    Comparator<GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }

  @Test(testName = "Проверка редактирования последней группы (с неизменяющимися значениями)")
  public void testGroupModificationWithSameValues() {
    app.group().verifyGroupPresence(newGroup, 1);
    List<GroupData> before = app.group().getGroupsList();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),
            "Relatives",
            "<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>",
            "<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>"
    );
    app.group().modifyGroup(before.size() - 1, group);
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size() - 1);
    before.add(group);
    Comparator<GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }
}