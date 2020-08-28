package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GroupCreationTests {
  private WebDriver driver;
  private String baseUrl;

  @BeforeClass(alwaysRun = true)
  public void setUp() {
    
    driver = new FirefoxDriver();

//    driver = new ChromeDriver();

//    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
//    ieOptions.disableNativeEvents();
//    driver = new InternetExplorerDriver(ieOptions);

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    baseUrl = "http://localhost/addressbook";
    driver.get(baseUrl);
    login("admin","secret");
  }


  @Test
  public void testGroupCreation() {
    getElement(groupsPageNavLinkLoc).click();
//    getElement(topAddGroupBtnLoc).click(); /*только верхняя кнопка*/
//    getElement(bottomAddGroupBtnLoc).click(); /*только нижняя кнопка*/
//    getFirstElement(addGroupBtnLoc).click(); /*первая кнопка из найденных*/
    getAnyElement(addGroupBtnLoc).click(); /*любая кнопка из найденных*/
    clearAndType(groupNameLoc, "Relatives");
    clearAndType(groupHeaderLoc, "<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>");
    clearAndType(groupFooterLoc, "<a href=\"edit.php\">add contact</a>  <a href=\"group.php?new=New+group\" " +
            "target=\"_self\">add group</a>");
    getElement(createGroupBtnLoc).click();
    getElement(returnToGroupsPageBtnLoc).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    driver.quit();
  }

  //region Locators
  public By passwordLoc = By.name("pass");
  public By usernameLoc = By.name("user");
  public By loginBtnLoc = By.cssSelector("input[type=submit]");
  public By groupsPageNavLinkLoc = By.cssSelector("#nav a[href='group.php']");
  public By addGroupBtnLoc = By.name("new");
  //  public By topAddGroupBtnLoc = By.cssSelector("input[name=new]:nth-child(1)");
//  public By bottomAddGroupBtnLoc = By.cssSelector("input[name=new]:nth-child(2)");
  public By groupNameLoc = By.name("group_name");
  public By groupHeaderLoc = By.name("group_header");
  public By groupFooterLoc = By.name("group_footer");
  public By createGroupBtnLoc = By.name("submit");
  public By returnToGroupsPageBtnLoc = By.cssSelector("#content a[href='group.php']");
  //endregion

  //region Helper methods
  public void login(String username, String password) {
    getElement(usernameLoc).click();
    getElement(usernameLoc).clear();
    getElement(usernameLoc).sendKeys(username);
    clearAndType(passwordLoc,password);
    getElement(loginBtnLoc).click();
  }
  //endregion

  // region Base methods
  public WebElement getElement(By by) {
    return driver.findElement(by);
  }

  public List<WebElement> getElements(By by) {
    return driver.findElements(by);
  }

  public WebElement getAnyElement(By by) {
    return getElements(by).stream().findAny().get();
  }

  public WebElement getFirstElement(By by) {
    return getElements(by).stream().findFirst().get();
  }

  public void clearAndType(By by, String value) {
    getElement(by).click();
    getElement(by).clear();
    getElement(by).sendKeys(value);
  }

//  public boolean isElementPresent(By by) {
//    try {
//      driver.findElement(by);
//      return true;
//    } catch (NoSuchElementException e) {
//      return false;
//    }
//  }
  //endregion
}
