package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupModificationTests extends TestBase {

    @Test(testName = "Проверка редактирования группы")
    public void testGroupModification() {
      app.nav().goToGroupsPage();
      app.group().selectAnyGroup();
      app.group().initGroupModification();
      app.group().fillGroupForm(new GroupData(
              "Friends",
              "<h1>FRIENDS</h1><p>Created by Lissa Rider</p></p>",
              "<a href=\"index.php\">home</a>"
      ));
      app.group().submitGroupModification();
      app.group().returnToGroupsPage();
    }

  @Test(testName = "Проверка редактирования группы (с неизменяющимися значениями)")
  public void testGroupModificationWithSameValues() {
    app.nav().goToGroupsPage();
    app.group().selectAnyGroup();
    app.group().initGroupModification();
    app.group().fillGroupForm(new GroupData(
            "Relatives",
            "<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>",
            "<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>"
    ));
    app.group().submitGroupModification();
    app.group().returnToGroupsPage();
  }
}