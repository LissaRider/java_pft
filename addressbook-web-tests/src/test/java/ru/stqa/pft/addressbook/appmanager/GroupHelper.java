package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupHelper extends HelperBase {

  //<editor-fold desc="Locators">
  public By addGroupBtnLoc = By.name("new");
//  public By topAddGroupBtnLoc = By.cssSelector("input[name=new]:nth-child(1)");
//  public By bottomAddGroupBtnLoc = By.cssSelector("input[name=new]:nth-child(2)");
  public By groupNameLoc = By.name("group_name");
  public By groupHeaderLoc = By.name("group_header");
  public By groupFooterLoc = By.name("group_footer");
  public By createGroupBtnLoc = By.name("submit");
  public By returnToGroupsPageLinkLoc = By.cssSelector("#content a[href='group.php']");
//  public By topDeleteGroupBtnLoc = By.cssSelector("input[name=delete]:nth-child(1)");
//  public By bottomDeleteGroupBtnLoc = By.cssSelector("input[name=delete]:nth-child(2)");
  public By groupCheckboxLoc = By.name("selected[]");
  public By deleteGroupBtnLoc = By.name("delete");
  public By editGroupBtnLoc = By.name("edit");
//  public By topEditGroupBtnLoc = By.cssSelector("input[name=edit]:nth-child(1)");
//  public By bottomEditGroupBtnLoc = By.cssSelector("input[name=edit]:nth-child(2)");
  public By updateGroupBtnLoc = By.name("update");
  //</editor-fold>

  public GroupHelper(WebDriver driver) {
    super(driver);
  }

  //<editor-fold desc="Methods">
  public void initGroupCreation() {
//    getElement(topAddGroupBtnLoc).click(); /*только верхняя кнопка*/
//    getElement(bottomAddGroupBtnLoc).click(); /*только нижняя кнопка*/
//    getFirstElement(addGroupBtnLoc).click(); /*первая кнопка из найденных*/
    getAnyElement(addGroupBtnLoc).click(); /*любая кнопка из найденных*/
  }

  public void fillGroupForm(GroupData groupData) {
    clearAndType(groupNameLoc, groupData.getName());
    clearAndType(groupHeaderLoc, groupData.getHeader());
    clearAndType(groupFooterLoc, groupData.getFooter());
  }

  public void submitGroupCreation() {
    getElement(createGroupBtnLoc).click();
  }

  public void selectAnyGroup() {
    getAnyElement(groupCheckboxLoc).click();
  }

  public void submitGroupDeletion() {
//    getElement(topDeleteGroupBtnLoc).click(); /*только верхняя кнопка*/
//    getElement(bottomDeleteGroupBtnLoc).click(); /*только нижняя кнопка*/
//    getFirstElement(deleteGroupBtnLoc).click(); /*первая кнопка из найденных*/
    getAnyElement(deleteGroupBtnLoc).click();
  }

  public void returnToGroupsPage() {
    getElement(returnToGroupsPageLinkLoc).click();
  }

  public void initGroupModification() {
//    getElement(topEditGroupBtnLoc).click(); /*только верхняя кнопка*/
//    getElement(bottomEditGroupBtnLoc).click(); /*только нижняя кнопка*/
//    getFirstElement(editGroupBtnLoc).click(); /*первая кнопка из найденных*/
    getAnyElement(editGroupBtnLoc).click(); /*любая кнопка из найденных*/
  }

  public void submitGroupModification() {
    getElement(updateGroupBtnLoc).click();
  }
  //</editor-fold>
}