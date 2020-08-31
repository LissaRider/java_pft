package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupCreationTests extends TestBase {

  @Test(testName = "Проверка создание группы")
  public void testGroupCreation() {
    app.nav().goToGroupsPage();
    app.group().initGroupCreation();
    app.group().fillGroupForm(new GroupData(
            "Relatives",
            "<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>",
            "<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>"
    ));
    app.group().submitGroupCreation();
    app.group().returnToGroupsPage();
  }
}
