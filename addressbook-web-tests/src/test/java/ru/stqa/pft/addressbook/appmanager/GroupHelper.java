package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupHelper extends HelperBase {

  //<editor-fold desc="Locators">
  public By addGroupBtnLoc = By.name("new");
//  public By topAddGroupBtnLoc = By.xpath(".//input[@name='new'][1]");
//  public By bottomAddGroupBtnLoc = By.xpath(".//input[@name='new'][last()]");
  public By groupNameLoc = By.name("group_name");
  public By groupHeaderLoc = By.name("group_header");
  public By groupFooterLoc = By.name("group_footer");
  public By createGroupBtnLoc = By.name("submit");
  public By returnToGroupsPageLinkLoc = By.xpath(".//*[@id='content']//a[@href='group.php']");
//  public By topDeleteGroupBtnLoc = By.xpath(".//input[@name='delete'][1]");
//  public By bottomDeleteGroupBtnLoc = By.xpath(".//input[@name='delete'][last()]");
  public By groupCheckboxLoc = By.name("selected[]");
  public By deleteGroupBtnLoc = By.name("delete");
  public By editGroupBtnLoc = By.name("edit");
//  public By topEditGroupBtnLoc = By.xpath(".//input[@name='edit'][1]");
//  public By bottomEditGroupBtnLoc = By.xpath(".//input[@name='edit'][last()]");
  public By updateGroupBtnLoc = By.name("update");
  //</editor-fold>

  public GroupHelper(ApplicationManager app) {
    super(app);
  }

  //<editor-fold desc="Methods">
  public void initGroupCreation() {
//    click(topAddGroupBtnLoc); /*только верхняя кнопка*/
//    click(bottomAddGroupBtnLoc); /*только нижняя кнопка*/
//    click(addGroupBtnLoc); /*первая кнопка из найденных*/
    click(addGroupBtnLoc); /*любая кнопка из найденных*/
  }

  public void fillGroupForm(GroupData groupData) {
    clearAndType(groupNameLoc, groupData.getName());
    clearAndType(groupHeaderLoc, groupData.getHeader());
    clearAndType(groupFooterLoc, groupData.getFooter());
  }

  public void submitGroupCreation() {
    click(createGroupBtnLoc);
  }

  public void selectAnyGroup() {
    click(groupCheckboxLoc);
  }

  public void submitGroupDeletion() {
//    click(topDeleteGroupBtnLoc); /*только верхняя кнопка*/
//    click(bottomDeleteGroupBtnLoc); /*только нижняя кнопка*/
//    click(deleteGroupBtnLoc); /*первая кнопка из найденных*/
    click(deleteGroupBtnLoc); /*любая кнопка из найденных*/
  }

  public void returnToGroupsPage() {
    click(returnToGroupsPageLinkLoc);
  }

  public void initGroupModification() {
//    click(topEditGroupBtnLoc); /*только верхняя кнопка*/
//    click(bottomEditGroupBtnLoc); /*только нижняя кнопка*/
//    click(editGroupBtnLoc); /*первая кнопка из найденных*/
    click(editGroupBtnLoc); /*любая кнопка из найденных*/
  }

  public void submitGroupModification() {
    click(updateGroupBtnLoc);
  }

  public void createGroup(GroupData group) {
    app.nav().goToGroupsPage();
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupsPage();
  }

  public void modifyGroup(GroupData groupData) {
    selectAnyGroup();
    initGroupModification();
    fillGroupForm(groupData);
    submitGroupModification();
    returnToGroupsPage();
  }

  public void removeGroup() {
    selectAnyGroup();
    submitGroupDeletion();
    returnToGroupsPage();
  }

  public boolean isAnyGroupPresent() {
    return isAnyElementPresent(groupCheckboxLoc);
  }

  public void verifyGroupPresence(GroupData newGroup) {
    app.nav().goToGroupsPage();
    if (!isAnyGroupPresent()) createGroup(newGroup);
  }
  //</editor-fold>
}