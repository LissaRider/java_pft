package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  //<editor-fold desc="Locators">
  public By groupsPageLinkLoc = By.cssSelector("#nav a[href='group.php']");
//  public By groupsPageLinkLoc = By.xpath(".//*[@id=nav]//a[href='group.php']");
  public By editContactPageLinkLoc = By.cssSelector("#nav a[href='edit.php']");
//  public By editContactPageLinkLoc = By.xpath(".//*[@id=nav]//a[href='edit.php']");
  public By homePageLinkLoc = By.cssSelector("#nav a[href='./']");
//  public By homePageLinkLoc = By.xpath(".//*[@id=nav]//a[href='./']");
  public By pageTitleLoc = By.tagName("h1");
  //</editor-fold>

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  //<editor-fold desc="Methods">
  public String getPageTitle() {
    return getElement(pageTitleLoc).getText();
  }

  public void goToGroupsPage() {
    if (isAnyElementPresent(pageTitleLoc)
            && getPageTitle().equals("Groups")
            && isAnyElementPresent(app.group().addGroupBtnLoc)) {
      return;
    }
    click(groupsPageLinkLoc);
  }

  public void goToEditContactPage() {
    if (isAnyElementPresent(pageTitleLoc)
            && getPageTitle().equals("Edit / add address book entry")
            && isAnyElementPresent(app.contact().createContactBtnLoc)) {
      return;
    }
    click(editContactPageLinkLoc);
  }

  public void goToHomePage() {
    if (isAnyElementPresent(app.contact().contactsTableLoc)) {
      return;
    }
    click(homePageLinkLoc);
  }
  //</editor-fold>
}