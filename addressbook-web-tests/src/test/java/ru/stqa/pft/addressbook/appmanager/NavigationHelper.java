package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  //<editor-fold desc="Locators">
  public By groupsPageLinkLoc = By.cssSelector("#nav a[href='group.php']");
  public By editContactPageLinkLoc = By.cssSelector("#nav a[href='edit.php']");
  public By homePageLinkLoc = By.cssSelector("#nav a[href='./']");
  public By pageTitleLoc = By.tagName("h1");
  //</editor-fold>

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  //<editor-fold desc="Methods">
  public String getPageTitle() {
    return getElement(pageTitleLoc).getText();
  }

  public void groupsPage() {
    if (isAnyElementPresent(pageTitleLoc)
            && getPageTitle().equals("Groups")
            && isAnyElementPresent(app.group().addGroupBtnLoc)) {
      return;
    }
    click(groupsPageLinkLoc);
    waitForPageLoad(); // IE
  }

  public void editPage() {
    if (isAnyElementPresent(pageTitleLoc)
            && getPageTitle().equals("Edit / add address book entry")
            && isAnyElementPresent(app.contact().createContactBtnLoc)) {
      return;
    }
    click(editContactPageLinkLoc);
  }

  public void homePage() {
    if (isAnyElementPresent(app.contact().contactsTableLoc)) {
      return;
    }
    click(homePageLinkLoc);
    waitForPageLoad(); // IE
  }
  //</editor-fold>
}