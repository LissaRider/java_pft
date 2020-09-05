package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.models.LoginData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  WebDriver driver;


  private LoginHelper loginHelper;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private HelperBase helperBase;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      driver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      driver = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      InternetExplorerOptions ieOptions = new InternetExplorerOptions();
      ieOptions.disableNativeEvents();
      driver = new InternetExplorerDriver(ieOptions);
    }

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    String baseUrl = "http://localhost/addressbook";
    driver.get(baseUrl);

    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    contactHelper = new ContactHelper(driver);
    loginHelper = new LoginHelper(driver);
    helperBase = new HelperBase(driver);

    loginHelper.login(new LoginData("admin", "secret"));
  }

  public void stop() {
    driver.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper nav() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public LoginHelper login() {
    return loginHelper;
  }

  public HelperBase base() {
    return helperBase;
  }
}