package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactCreationTests {

  private WebDriver driver;
  private String baseUrl;

  @BeforeClass(alwaysRun = true)
  public void setUp() {

    //region Drivers
//    driver = new FirefoxDriver();

    driver = new ChromeDriver();

//    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
//    ieOptions.disableNativeEvents();
//    driver = new InternetExplorerDriver(ieOptions);
    //endregion

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    baseUrl = "http://localhost/addressbook";
    driver.get(baseUrl);

    login(new LoginData("admin", "secret"));
  }

  @Test
  public void testContactCreation() throws InterruptedException {
    openEditContactPage();
    System.out.println(System.getProperty("user.dir").replace("\\", "/") +
                    "/src/test/resources/dark_alice.jpg");
    fillContactForm(new ContactData(
            "Alice",
            "Batkovna",
            "Fabler",
            "LisAnieL",
            System.getProperty("user.dir").replace("\\", "/") +
                    "/src/test/resources/dark_alice.jpg",
            "Middle QA Automation Engineer",
            "Bank",
            "Moscow, Chertanovo Tsentralnoye District",
            "8(495) 000-00-00",
            "8(999) 000-00-00",
            "8(800) 888-88-88",
            "8(800) 888-88-80",
            "lissarider@gmail.com",
            "lisaniel.lisaniel@gmail.com",
            "lisaniel@mail.ru",
            "www.fairytales.com",
            9,
            "March",
            "1996",
            "8",
            "March",
            "2026",
            "Moscow, Biryulyovo Zapadnoye District",
            "8(909) 999-99-99",
            "\"Who in the world am I?\" Ah, that is the great puzzle!"));
    submitContactCreation();
    returnToHomePage();
//    returnToEditContactPage();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() { driver.quit(); }

  //region Locators
  public By passwordLoc = By.name("pass");
  public By usernameLoc = By.name("user");
  public By loginBtnLoc = By.cssSelector("input[type=submit]");
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
//  public By topCreateContactBtnLoc = By.cssSelector("input[name=submit]:nth-child(1)");
//  public By bottomCreateContactBtnLoc = By.cssSelector("input[name=submit]:nth-child(2)");
  public By createContactBtnLoc = By.name("submit");
  public By returnToHomePageLinkLoc = By.cssSelector("#content a[href='index.php']");
//  public By returnToEditContactPageLinkLoc = By.cssSelector("#content a[href='edit.php']");
  //endregion

  //region Login methods
  public void login(LoginData loginData) {
    fillLoginForm(loginData.getUsername(), loginData.getPassword());
    submitLogin();
  }

  private void fillLoginForm(String username, String password) {
    clearAndType(usernameLoc, username);
    clearAndType(passwordLoc,password);
  }

  private void submitLogin() { getElement(loginBtnLoc).click(); }
  //endregion

  //region Contact methods
  public void openEditContactPage() { getElement(editContactPageLinkLoc).click(); }

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
  }

  public void returnToHomePage() { getElement(returnToHomePageLinkLoc).click(); }

//  public void returnToEditContactPage() { getElement(returnToEditContactPageLinkLoc).click(); }
  //endregion

  // region Base methods
  public WebElement getElement(By by) { return driver.findElement(by); }

  public List<WebElement> getElements(By by) { return driver.findElements(by); }

  public WebElement getAnyElement(By by) { return getElements(by).stream().findAny().get(); }

//  public WebElement getFirstElement(By by) { return getElements(by).stream().findFirst().get(); }

  public void clearAndType(By by, String value) {
    getElement(by).click();
    getElement(by).clear();
    getElement(by).sendKeys(value);
  }

  public void uploadFile(By by, String path) { getElement(by).sendKeys(path); }

  public void selectByText(By by, String text) { new Select(getElement(by)).selectByVisibleText(text); }

  public void selectByValue(By by, String value) { new Select(getElement(by)).selectByValue(value); }

  public void selectByIndex(By by, int index) { new Select(getElement(by)).selectByIndex(index); }

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