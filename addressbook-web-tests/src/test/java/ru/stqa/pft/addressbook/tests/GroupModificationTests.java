package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupModificationTests extends TestBase {

  public GroupData newGroup = new GroupData(
          "Relatives",
          null,
          null
  );

  @Test(testName = "Проверка редактирования группы")
  public void testGroupModification() {
    app.group().verifyGroupPresence(newGroup);;
    app.group().modifyGroup(new GroupData(
            "Friends",
            "<h1>FRIENDS</h1><p>Created by Lissa Rider</p></p>",
            "<a href=\"index.php\">home</a>"
    ));
  }

  @Test(testName = "Проверка редактирования группы (с неизменяющимися значениями)")
  public void testGroupModificationWithSameValues() {
    app.group().verifyGroupPresence(newGroup);
    app.group().modifyGroup(new GroupData(
            "Relatives",
            "<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>",
            "<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
                    "target=\"_self\">add group</a>"
    ));
  }
}