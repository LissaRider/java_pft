package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.LoginData;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  //<editor-fold desc="Login locators">
  public By passwordLoc = By.name("pass");
  public By usernameLoc = By.name("user");
  public By loginBtnLoc = By.cssSelector("input[type=submit]");
  //</editor-fold>

  //<editor-fold desc="Contact locators">
  public By editContactPageLinkLoc = By.cssSelector("#nav [href='edit.php']");
  public By firstNameLoc = By.name("firstname");
  public By middleNameLoc = By.name("middlename");
  public By lastNameLoc = By.name("lastname");
  public By nicknameLoc = By.name("nickname");
  public By inputFileLoc = By.name("photo");
  public By jobTitleLoc = By.name("title");
  public By companyNameLoc = By.name("company");
  public By mainAddressLoc = By.name("address");
  public By homePhoneLoc = By.name("home");
  public By mobilePhoneLoc = By.name("mobile");
  public By workPhoneLoc = By.name("work");
  public By faxNumberLoc = By.name("fax");
  public By emailLoc = By.name("email");
  public By email2Loc = By.name("email2");
  public By email3Loc = By.name("email3");
  public By webSiteLoc = By.name("homepage");
  public By birthDayLoc = By.name("bday");
  public By birthMonthLoc = By.name("bmonth");
  public By birthYearLoc = By.name("byear");
  public By anniversaryDayLoc = By.name("aday");
  public By anniversaryMonthLoc = By.name("amonth");
  public By anniversaryYearLoc = By.name("ayear");
  public By contactsGroupLoc = By.name("new_group");
  public By groupsListLoc = By.cssSelector("select[name=new_group] option");
  public By adAddressLoc = By.name("address2");
  public By adPhoneLoc = By.name("phone2");
  public By notesLoc = By.name("notes");
  public By topCreateContactBtnLoc = By.cssSelector("input[name=submit]:nth-child(1)");
  public By bottomCreateContactBtnLoc = By.cssSelector("input[name=submit]:nth-child(2)");
  public By createContactBtnLoc = By.name("submit");
  public By returnToHomePageLinkLoc = By.cssSelector("#content a[href='index.php']");
  public By returnToEditContactPageLinkLoc = By.cssSelector("#content a[href='edit.php']");
  //</editor-fold>

  //<editor-fold desc="Group locators">
  public By groupsPageNavLinkLoc = By.cssSelector("#nav a[href='group.php']");
  public By addGroupBtnLoc = By.name("new");
  public By topAddGroupBtnLoc = By.cssSelector("input[name=new]:nth-child(1)");
  public By bottomAddGroupBtnLoc = By.cssSelector("input[name=new]:nth-child(2)");
  public By groupNameLoc = By.name("group_name");
  public By groupHeaderLoc = By.name("group_header");
  public By groupFooterLoc = By.name("group_footer");
  public By createGroupBtnLoc = By.name("submit");
  public By returnToGroupsPageLinkLoc = By.cssSelector("#content a[href='group.php']");
  public By topDeleteGroupBtnLoc = By.cssSelector("input[name=delete]:nth-child(1)");
  public By bottomDeleteGroupBtnLoc = By.cssSelector("input[name=delete]:nth-child(2)");
  public By groupCheckboxLoc = By.name("selected[]");
  public By deleteGroupBtnLoc = By.name("delete");
  //</editor-fold>

  private WebDriver driver;
  private String baseUrl;
  private String state;

  public void init() {

    //<editor-fold desc="Drivers">
    driver = new FirefoxDriver();

//    driver = new ChromeDriver();

/*    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
    ieOptions.disableNativeEvents();
    driver = new InternetExplorerDriver(ieOptions);*/
    //</editor-fold>

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    baseUrl = "http://localhost/addressbook";
    driver.get(baseUrl);
    WaitForPageLoad();

    login(new LoginData("admin", "secret"));
  }

  public void stop() {
    driver.quit();
  }

  //<editor-fold desc="Login methods">
  public void login(LoginData loginData) {
    fillLoginForm(loginData.getUsername(), loginData.getPassword());
    submitLogin();
  }

  public void fillLoginForm(String username, String password) {
    clearAndType(usernameLoc, username);
    clearAndType(passwordLoc, password);
  }

  public void submitLogin() {
    getElement(loginBtnLoc).click();
    WaitForPageLoad();
  }
  //</editor-fold>

  //<editor-fold desc="Group methods">
  public void goToGroupsPage() {
    getElement(groupsPageNavLinkLoc).click();
    WaitForPageLoad();
  }

  public void initGroupCreation() {
//    getElement(topAddGroupBtnLoc).click(); /*только верхняя кнопка*/
//    getElement(bottomAddGroupBtnLoc).click(); /*только нижняя кнопка*/
//    getFirstElement(addGroupBtnLoc).click(); /*первая кнопка из найденных*/
    getAnyElement(addGroupBtnLoc).click(); /*любая кнопка из найденных*/
    WaitForPageLoad();
  }

  public void fillGroupForm(GroupData groupData) {
    clearAndType(groupNameLoc, groupData.getName());
    clearAndType(groupHeaderLoc, groupData.getHeader());
    clearAndType(groupFooterLoc, groupData.getFooter());
  }

  public void submitGroupCreation() {
    getElement(createGroupBtnLoc).click();
    WaitForPageLoad();
  }

  public void selectAnyGroup() {
    getAnyElement(groupCheckboxLoc).click();
  }

  public void submitGroupDeletion() {
//    getElement(topDeleteGroupBtnLoc).click(); /*только верхняя кнопка*/
//    getElement(bottomDeleteGroupBtnLoc).click(); /*только нижняя кнопка*/
//    getFirstElement(deleteGroupBtnLoc).click(); /*первая кнопка из найденных*/
    getAnyElement(deleteGroupBtnLoc).click();
    WaitForPageLoad();
  }

  public void returnToGroupsPage() {
    getElement(returnToGroupsPageLinkLoc).click();
    WaitForPageLoad();
  }
  //</editor-fold>

  //<editor-fold desc="Contact methods">
  public void openEditContactPage() {
    getElement(editContactPageLinkLoc).click();
    WaitForPageLoad();
  }

  public void fillContactForm(ContactData contactData) {
    clearAndType(firstNameLoc, contactData.getFirstName());
    clearAndType(middleNameLoc, contactData.getMiddleName());
    clearAndType(lastNameLoc, contactData.getLastName());
    clearAndType(nicknameLoc, contactData.getNickname());
    uploadFile(inputFileLoc, contactData.getFilePath());
    clearAndType(jobTitleLoc, contactData.getJobTitle());
    clearAndType(companyNameLoc, contactData.getCompanyName());
    clearAndType(mainAddressLoc, contactData.getMainAddress());
    clearAndType(homePhoneLoc, contactData.getHomePhone());
    clearAndType(mobilePhoneLoc, contactData.getMobilePhone());
    clearAndType(workPhoneLoc, contactData.getWorkPhone());
    clearAndType(faxNumberLoc, contactData.getFaxNumber());
    clearAndType(emailLoc, contactData.getEmail());
    clearAndType(email2Loc, contactData.getEmail2());
    clearAndType(email3Loc, contactData.getEmail3());
    clearAndType(webSiteLoc, contactData.getWebSite());
    selectByIndex(birthDayLoc, contactData.getBirthDay());
    selectByText(birthMonthLoc, contactData.getBirthMonth());
    clearAndType(birthYearLoc, contactData.getBirthYear());
    selectByValue(anniversaryDayLoc, contactData.getAnniversaryDay());
    selectByValue(anniversaryMonthLoc, contactData.getAnniversaryMonth());
    clearAndType(anniversaryYearLoc, contactData.getAnniversaryYear());
//    selectByValue(contactsGroupLoc, "[none]");
//    selectByText(contactsGroupLoc, "[none]");
//    selectByIndex(contactsGroupLoc, 0);
    clearAndType(adAddressLoc, contactData.getAdAddress());
    clearAndType(adPhoneLoc, contactData.getAdPhone());
    clearAndType(notesLoc, contactData.getNotes());
  }

  public void submitContactCreation() {
//    getElement(topCreateContactBtnLoc).click(); /*только верхняя кнопка*/
//    getElement(bottomCreateContactBtnLoc).click(); /*только нижняя кнопка*/
//    getFirstElement(createContactBtnLoc).click(); /*первая кнопка из найденных*/
    getAnyElement(createContactBtnLoc).click(); /*любая кнопка из найденных*/
    WaitForPageLoad();
  }

  public void returnToHomePage() {
    getElement(returnToHomePageLinkLoc).click();
    WaitForPageLoad();
  }

  public void returnToEditContactPage() {
    getElement(returnToEditContactPageLinkLoc).click();
    WaitForPageLoad();
  }
  //</editor-fold>

  //<editor-fold desc="Base methods">
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

  public void uploadFile(By by, String path) {
    getElement(by).sendKeys(path);
  }

  public void selectByText(By by, String text) {
    new Select(getElement(by)).selectByVisibleText(text);
  }

  public void selectByValue(By by, String value) {
    new Select(getElement(by)).selectByValue(value);
  }

  public void selectByIndex(By by, int index) {
    new Select(getElement(by)).selectByIndex(index);
  }

  public boolean isElementPresent(By by) {
    try {
      getElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public void WaitForPageLoad() {
    int time = 30;
    try {
      WebDriverWait wait = new WebDriverWait(driver, time);
      //Checks every 500 ms whether predicate returns true
      //if returns exit otherwise keep trying till it returns true
      wait.until(driver -> {
        try {
          state = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
        } catch (IllegalStateException exception) {
          //Ignore
        } catch (NoSuchWindowException exception) {
          //when popup is closed, switch to last windows
          var windowHandles = driver.getWindowHandles();
          driver.switchTo().window((String) windowHandles.toArray()[windowHandles.size() - 1]);
        }
        //In IE7 there are chances we may get state as loaded instead of complete
        return (state.equalsIgnoreCase("complete") || state.equalsIgnoreCase("loaded"));
      });
    } catch (TimeoutException exception) {
      //sometimes Page remains in Interactive mode and never becomes Complete,
      // then we can still try to access the controls
      if (!state.equalsIgnoreCase("interactive"))
        throw exception;
    } catch (NullPointerException exception) {
      //sometimes Page remains in Interactive mode and never becomes Complete,
      //then we can still try to access the controls
      if (!state.equalsIgnoreCase("interactive"))
        throw exception;
    } catch (WebDriverException exception) {
      var windowHandles = driver.getWindowHandles();
      if (windowHandles.size() == 1) {
        driver.switchTo().window((String) windowHandles.toArray()[0]);
      }
      state = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
      if (!(state.equalsIgnoreCase("complete") || state.equalsIgnoreCase("loaded")))
        throw exception;
    }
  }
  //</editor-fold>
}
