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
  public By groupCheckboxLoc = By.name("selected[]");
  public By groupLoc(int id) {
    return By.cssSelector("input[value='" + id + "']");
  }
  //</editor-fold>

  public GroupHelper(ApplicationManager app) {
    super(app);
  }

  private Groups groupCache = null;

  //<editor-fold desc="Methods">
  public void initCreation() {
    click(addGroupBtnLoc);
  }

  public void fillForm(GroupData groupData) {
    clearAndType(groupNameLoc, groupData.getName());
    clearAndType(groupHeaderLoc, groupData.getHeader());
    clearAndType(groupFooterLoc, groupData.getFooter());
  }

  public void submitCreation() {
    click(createGroupBtnLoc);
  }

  public void selectById(int id) {
    getElement(groupLoc(id)).click();
  }

  public void submitDeletion() {
    click(deleteGroupBtnLoc);
  }

  public void returnToGroupsPage() {
    click(returnToGroupsPageLinkLoc);
  }

  public void initModification() {
    click(editGroupBtnLoc);
  }

  public void submitModification() {
    click(updateGroupBtnLoc);
  }

  private void selectAll() {
    var checkboxes = getElements(groupCheckboxLoc);
    for(var checkbox : checkboxes){
      if(!checkbox.isSelected())
        checkbox.click();
    }
  }

  public int count() {
    try {
      implicitlyWait(0);
      return getElements(groupLoc).size();
    } finally {
      implicitlyWait(10);
    }
  }

  public void create(GroupData group) {
    initCreation();
    fillForm(group);
    submitCreation();
    groupCache = null;
    verifyMessage("A new group has been entered into the address book.");
    returnToGroupsPage();
  }

  public void modify(GroupData group) {
    selectById(group.getId());
    initModification();
    fillForm(group);
    submitModification();
    groupCache = null;
    verifyMessage("Group record has been updated.");
    returnToGroupsPage();
  }

  public void delete(GroupData group) {
    selectById(group.getId());
    submitDeletion();
    groupCache = null;
    verifyMessage("Group has been removed.");
    returnToGroupsPage();
  }

  public void deleteAll() {
    selectAll();
    submitDeletion();
    groupCache = null;
    verifyMessage("Group has been removed.");
    returnToGroupsPage();
  }

  public Groups all() {
    if (groupCache != null)
      return new Groups(groupCache);
    groupCache = new Groups();
    try {
      implicitlyWait(0);
      List<WebElement> elements = getElements(groupLoc);
      for (WebElement element : elements) {
        String name = element.getText();
        int id = Integer.parseInt(element.findElement(groupInputLoc).getAttribute("value"));
        GroupData group = new GroupData().withId(id).withName(name);
        groupCache.add(group);
      }
      return new Groups(groupCache);
    } finally {
      implicitlyWait(10);
    }
  }
  //</editor-fold>
}