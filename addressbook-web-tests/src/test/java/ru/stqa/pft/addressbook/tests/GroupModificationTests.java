package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupDeletion() {
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
}