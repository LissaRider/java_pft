package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupCreationTests extends TestBase {

  @Test(testName = "Проверка создание группы")
  public void testGroupCreation() {
    app.nav().goToGroupsPage();
    int before = app.group().getGroupsCount();
    app.group().createGroup(new GroupData(
            "Relatives",
            "<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>",
            "<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>"
    ));
    int after = app.group().getGroupsCount();
    Assert.assertEquals(after, before + 1);
  }

  @Test(testName = "Проверка создание группы (дефолтные значения)")
  public void testGroupCreationWithDefaultFields() {
    app.nav().goToGroupsPage();
    int before = app.group().getGroupsCount();
    app.group().createGroup(new GroupData(
            "Colleagues",
            null,
            null
    ));
    int after = app.group().getGroupsCount();
    Assert.assertEquals(after, before + 1);
  }
}
