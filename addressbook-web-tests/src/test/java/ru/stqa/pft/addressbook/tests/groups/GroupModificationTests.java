package ru.stqa.pft.addressbook.tests.groups;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    GroupData newGroup = new GroupData()
            .withName("Relatives")
            .withHeader("<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>")
            .withFooter("<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>");
    app.group().verifyPresence(newGroup, 1);
  }

  @Test(testName = "Проверка редактирования первой группы")
  public void testFirstGroupModification() {
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData()
            .withId(before.get(0).getId())
            .withName("Friends")
            .withHeader("<h1>FRIENDS</h1><p>Created by Lissa Rider</p></p>")
            .withFooter("<a href=\"index.php\">home</a>");
    app.group().modify(0, group);
    List<GroupData> after = app.group().list();

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
  public void testLastGroupModificationWithSameValues() {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData()
            .withId(before.get(index).getId())
            .withName("Relatives")
            .withHeader("<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>")
            .withFooter("<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(group);
    Comparator<GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }
}