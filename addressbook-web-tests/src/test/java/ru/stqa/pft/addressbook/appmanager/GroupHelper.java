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
  //</editor-fold>
}