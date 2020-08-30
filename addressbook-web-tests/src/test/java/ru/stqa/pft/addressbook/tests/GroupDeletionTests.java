package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.goToGroupsPage();
    app.selectAnyGroup();
    app.submitGroupDeletion();
    app.returnToGroupsPage();
  }
}

