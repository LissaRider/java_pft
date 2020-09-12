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
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
//      https://github.com/mozilla/geckodriver/releases
      driver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      driver = new ChromeDriver();
//      https://chromedriver.storage.googleapis.com/85.0.4183.87/chromedriver_win32.zip
    } else if (browser.equals(BrowserType.IE)) {
//      https://selenium-release.storage.googleapis.com/3.150/IEDriverServer_Win32_3.150.1.zip
      InternetExplorerOptions ieOptions = new InternetExplorerOptions();
      ieOptions.disableNativeEvents();
      driver = new InternetExplorerDriver(ieOptions);
    }

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    String baseUrl = "http://localhost/addressbook";
    driver.get(baseUrl);

    groupHelper = new GroupHelper(this);
    navigationHelper = new NavigationHelper(this);
    contactHelper = new ContactHelper(this);
    loginHelper = new LoginHelper(this);

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
}