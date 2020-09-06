package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  //<editor-fold desc="Locators">
  public By groupsPageLinkLoc = By.cssSelector("#nav a[href='group.php']");
  public By editContactPageLinkLoc = By.cssSelector("#nav a[href='edit.php']");
  public By homePageLinkLoc = By.cssSelector("#nav a[href='./']");
  public By pageTitleLoc = By.tagName("h1");
  //</editor-fold>

  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  public GroupHelper group() {
    return new GroupHelper(driver);
  }

  public ContactHelper contact() {
    return new ContactHelper(driver);
  }

  //<editor-fold desc="Methods">
  public String getPageTitle() {
    return getElement(pageTitleLoc).getText();
  }

  public void goToGroupsPage() {
    if (isAnyElementPresent(pageTitleLoc)
            && getPageTitle().equals("Groups")
            && isAnyElementPresent(group().addGroupBtnLoc)) {
      return;
    }
    click(groupsPageLinkLoc);
  }

  public void goToEditContactPage() {
    if (isAnyElementPresent(pageTitleLoc)
            && getPageTitle().equals("Edit / add address book entry")
            && isAnyElementPresent(contact().createContactBtnLoc)) {
      return;
    }
    click(editContactPageLinkLoc);
  }

  public void goToHomePage() {
    if (isAnyElementPresent(contact().contactsTableLoc)) {
      return;
    }
    click(homePageLinkLoc);
  }
  //</editor-fold>
}