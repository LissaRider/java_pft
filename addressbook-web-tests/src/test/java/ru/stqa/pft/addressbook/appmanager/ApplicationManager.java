package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.models.LoginData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  public final Properties properties;

  WebDriver driver;

  private LoginHelper loginHelper;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private DbHelper dbHelper;

  public ApplicationManager() {
    properties = new Properties();
  }

  public void init() throws IOException {
    loadProperties("target", "local");
    loadProperties("timeout", "timeout");
    var baseUrl = getProperty("web.baseUrl");
    var browser = getProperty("web.browser");
    var timeout = getProperty("wait.implicitly");
    var adminLogin = getProperty("web.adminLogin");
    var adminPassword = getProperty("web.adminPassword");
    var geckoDriverPath = getProperty("web.geckoDriverPath");
    var chromeDriverPath = getProperty("web.chromeDriverPath");
    var ieDriverPath = getProperty("web.ieDriverPath");

    if (browser != null && !browser.isEmpty()) {
      switch (browser) {
        case BrowserType.FIREFOX:
          // https://github.com/mozilla/geckodriver/releases
          // driver: geckodriver-v0.27.0-win32
          setDriverPath("gecko", geckoDriverPath);
          driver = new FirefoxDriver();
          break;
        case BrowserType.CHROME:
          // https://chromedriver.chromium.org/downloads
          // driver:  chromedriver-v86.0.4240.22-win32
          setDriverPath("chrome", chromeDriverPath);
          driver = new ChromeDriver();
          break;
        case BrowserType.IE:
          setDriverPath("ie", ieDriverPath);
          // https://selenium-release.storage.googleapis.com/index.html
          // driver: iedriverserver-v3.9.0-win32
          driver = new InternetExplorerDriver();
          break;
        default:
          throw new IOException("Unrecognized browser: " + browser);
      }
    } else {
      throw new WebDriverException("Property 'web.browser' is null or not set (" + configFile("target", "local") + ")");
    }

    if (timeout != null && !timeout.isEmpty()) {
      driver.manage().timeouts().implicitlyWait(Long.parseLong(timeout), TimeUnit.SECONDS);
    }

    if (baseUrl != null && !baseUrl.isEmpty()) {
      driver.get(baseUrl);
    } else {
      throw new WebDriverException(
              "Property 'web.baseUrl' is null or not set (" + configFile("target", "local") + ")");
    }

    groupHelper = new GroupHelper(this);
    navigationHelper = new NavigationHelper(this);
    contactHelper = new ContactHelper(this);
    loginHelper = new LoginHelper(this);
    dbHelper = new DbHelper();

    loginHelper.login(new LoginData(adminLogin, adminPassword));
  }

  private void setDriverPath(String driver, String path) {
    System.setProperty(String.format("webdriver.%s.driver", driver), path);
  }

  private String getProperty(String key) {
    return properties.getProperty(key);
  }

  public void loadProperties(String key, String def) throws IOException {
    properties.load(new FileReader(new File(configFile(key, def))));
  }

  public String configFile(String key, String def) {
    return String.format("src/test/resources/config/%s.properties", System.getProperty(key, def));
  }

  public void stop() {
    driver.quit();
  }

  public DbHelper db() {
    return dbHelper;
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public LoginHelper login() {
    return loginHelper;
  }
}