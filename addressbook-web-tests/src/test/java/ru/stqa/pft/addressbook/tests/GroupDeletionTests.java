package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupDeletionTests extends TestBase {

  public GroupData newGroup = new GroupData(
          "Relatives",
          null,
          null
  );

  @Test(testName = "Проверка удаления группы")
  public void testGroupDeletion() {
    app.group().verifyGroupPresence(newGroup);
    app.group().removeGroup();
  }
}

