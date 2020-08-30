package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.models.LoginData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  WebDriver driver;

  private LoginHelper loginHelper;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private HelperBase helperBase;

  public void init() {

    //<editor-fold desc="Drivers">
    driver = new FirefoxDriver();

//    driver = new ChromeDriver();

/*    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
    ieOptions.disableNativeEvents();
    driver = new InternetExplorerDriver(ieOptions);*/
    //</editor-fold>

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