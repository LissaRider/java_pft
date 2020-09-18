package ru.stqa.pft.addressbook.tests.groups;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test(testName = "Проверка создания группы")
  public void testGroupCreation() {
    app.goTo().groupsPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData()
            .withName("Relatives")
            .withHeader("<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>")
            .withFooter("<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
            "target=\"_self\">add group</a>");

    app.group().create(group);
    List<GroupData> after = app.group().list();

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
    app.goTo().groupsPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("Colleagues");
    app.group().create(group);
    List<GroupData> after = app.group().list();

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
