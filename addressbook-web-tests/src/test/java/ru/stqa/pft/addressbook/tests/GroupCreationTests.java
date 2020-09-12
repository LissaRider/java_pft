package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test(testName = "Проверка создания группы")
  public void testGroupCreation() {
    app.nav().goToGroupsPage();
    List<GroupData> before = app.group().getGroupsList();
    app.group().createGroup(new GroupData(
            "Relatives",
            "<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>",
            "<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>"
    ));
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

  @Test(testName = "Проверка создания группы (дефолтные значения)")
  public void testGroupCreationWithDefaultFields() {
    app.nav().goToGroupsPage();
    List<GroupData> before = app.group().getGroupsList();
    app.group().createGroup(new GroupData(
            "Colleagues",
            null,
            null
    ));
    List<GroupData> after = app.group().getGroupsList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
