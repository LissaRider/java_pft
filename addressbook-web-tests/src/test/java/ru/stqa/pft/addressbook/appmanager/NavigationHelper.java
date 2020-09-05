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

  //<editor-fold desc="Methods">
  public void goToGroupsPage() {
    if (isElementPresent(pageTitleLoc)
            && getPageTitle().equals("Groups")
            && isElementPresent(GroupHelper.addGroupBtnLoc)) {
      return;
    }
    click(groupsPageLinkLoc);

  }

  public String getPageTitle() {
    return getElement(pageTitleLoc).getText();
  }

  public void goToEditContactPage() {
    if (isElementPresent(pageTitleLoc)
            && getPageTitle().equals("Edit / add address book entry")
            && isElementPresent(ContactHelper.createContactBtnLoc)) {
      return;
    }
    click(editContactPageLinkLoc);
  }

  public void goToHomePage() {
    if (isElementPresent(ContactHelper.contactsTableLoc)) {
      return;
    }
    click(homePageLinkLoc);
  }
  //</editor-fold>
}