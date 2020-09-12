package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test(testName = "Проверка создания группы")
  public void testGroupCreation() {
    app.nav().goToGroupsPage();
    List<GroupData> before = app.group().getGroupsList();
    GroupData group = new GroupData(
            "Relatives",
            "<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>",
            "<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>"
    );
    app.group().createGroup(group);
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size() + 1);
//    int max = 0;
//    for (GroupData g : after) {
//      if (g.getId() > max)
//        max = g.getId();
//    }
//    group.setId(max);
//    group.setId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
    before.add(group);
    Comparator<GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }

  @Test(testName = "Проверка создания группы (дефолтные значения)")
  public void testGroupCreationWithDefaultFields() {
    app.nav().goToGroupsPage();
    List<GroupData> before = app.group().getGroupsList();
    GroupData group = new GroupData(
            "Colleagues",
            null,
            null
    );
    app.group().createGroup(group);
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size() + 1);
//    int max = 0;
//    for (GroupData g : after) {
//      if (g.getId() > max)
//        max = g.getId();
//    }
//    group.setId(max);
//    group.setId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
    before.add(group);
    Comparator<GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }
}
