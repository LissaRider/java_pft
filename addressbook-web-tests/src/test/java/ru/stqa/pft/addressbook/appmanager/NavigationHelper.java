package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  //<editor-fold desc="Locators">
  public By groupsPageLinkLoc = By.cssSelector("#nav a[href='group.php']");
  public By editContactPageLinkLoc = By.cssSelector("#nav a[href='edit.php']");
  public By homePageLinkLoc = By.cssSelector("#nav a[href='./']");
  //</editor-fold>

  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  //<editor-fold desc="Methods">
  public void goToGroupsPage() {
    getElement(groupsPageLinkLoc).click();
  }

  public void goToEditContactPage() {
    getElement(editContactPageLinkLoc).click();
  }

  public void goToHomePage() {
    getElement(homePageLinkLoc).click();
  }
  //</editor-fold>
}