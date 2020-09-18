package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

  //<editor-fold desc="Locators">
  public By addGroupBtnLoc = By.name("new");
  public By groupNameLoc = By.name("group_name");
  public By groupHeaderLoc = By.name("group_header");
  public By groupFooterLoc = By.name("group_footer");
  public By createGroupBtnLoc = By.name("submit");
  public By returnToGroupsPageLinkLoc = By.cssSelector("#content a[href='group.php']");
  public By deleteGroupBtnLoc = By.name("delete");
  public By editGroupBtnLoc = By.name("edit");
  public By updateGroupBtnLoc = By.name("update");
  public By groupLoc = By.className("group");
  public By groupInputLoc = By.tagName("input");
  //</editor-fold>

  public GroupHelper(ApplicationManager app) {
    super(app);
  }

  public By groupLoc(int id) {
    return By.cssSelector("input[value='" + id + "']");
  }

  //<editor-fold desc="Methods">
  public void initGroupCreation() {
    click(addGroupBtnLoc);
  }

  public void fillGroupForm(GroupData groupData) {
    clearAndType(groupNameLoc, groupData.getName());
    clearAndType(groupHeaderLoc, groupData.getHeader());
    clearAndType(groupFooterLoc, groupData.getFooter());
  }

  public void submitGroupCreation() {
    click(createGroupBtnLoc);
  }

  public void selectGroupById(int id) {
    getElement(groupLoc(id)).click();
  }

  public void submitGroupDeletion() {
    click(deleteGroupBtnLoc);
  }

  public void returnToGroupsPage() {
    click(returnToGroupsPageLinkLoc);
  }

  public void initGroupModification() {
    click(editGroupBtnLoc);
  }

  public void submitGroupModification() {
    click(updateGroupBtnLoc);
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    verifyMessage("A new group has been entered into the address book.");
    returnToGroupsPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    verifyMessage("Group record has been updated.");
    returnToGroupsPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    submitGroupDeletion();
    verifyMessage("Group has been removed.");
    returnToGroupsPage();
  }

  public Groups all() {
    Groups groups = new Groups();
    try {
      implicitlyWait(0);
      List<WebElement> elements = getElements(groupLoc);
      for (WebElement element : elements) {
        String name = element.getText();
        int id = Integer.parseInt(element.findElement(groupInputLoc).getAttribute("value"));
        GroupData group = new GroupData().withId(id).withName(name);
        groups.add(group);
      }
      return groups;
    } finally {
      implicitlyWait(10);
    }
  }
  //</editor-fold>
}