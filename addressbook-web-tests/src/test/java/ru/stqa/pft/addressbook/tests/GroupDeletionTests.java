package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

  @Test(testName = "Проверка удаления группы")
  public void testGroupDeletion() {
    app.nav().goToGroupsPage();
    app.group().selectAnyGroup();
    app.group().submitGroupDeletion();
    app.group().returnToGroupsPage();
  }
}

