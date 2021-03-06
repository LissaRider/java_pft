package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  public final Properties properties;

  private WebDriver driver;

  private HttpSession http;
  private RegistrationHelper reg;
  private FtpHelper ftp;
  private MailHelper mail;
  private JamesHelper james;
  private UserHelper user;
  private DbHelper db;
  private LoginHelper login;
  private NavigationHelper nav;
  private SoapHelper soap;

  public ApplicationManager() {
    properties = new Properties();
  }

  public void init() throws IOException {
    loadProperties("target", "local");
    loadProperties("timeout", "timeout");
  }

  public WebDriver getDriver() {
    var baseUrl = getProperty("web.baseUrl");
    var browser = getProperty("web.browser");
    var timeout = getProperty("wait.implicitly");
    var geckoDriverPath = getProperty("web.geckoDriverPath");
    var chromeDriverPath = getProperty("web.chromeDriverPath");
    var ieDriverPath = getProperty("web.ieDriverPath");

    if (driver == null) {
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
            try {
              throw new IOException("Unrecognized browser: " + browser);
            } catch (IOException e) {
              e.printStackTrace();
            }
        }
      } else {
        throw new WebDriverException(
                "Property 'web.browser' is null or not set (" + configFile("target", "local") + ")");
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
    }
    return driver;
  }

  private void setDriverPath(String driver, String path) {
    System.setProperty(String.format("webdriver.%s.driver", driver), path);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public void loadProperties(String key, String def) throws IOException {
    properties.load(new FileReader(new File(configFile(key, def))));
  }

  public String configFile(String key, String def) {
    return String.format("src/test/resources/config/%s.properties", System.getProperty(key, def));
  }

  public void stop() {
    if (driver != null) {
      driver.quit();
      // taskkill /f /im chromedriver.exe /im geckodriver.exe /im IEDriverServer.exe
    }
  }

  public RegistrationHelper registration() {
    if (reg == null) {
      reg = new RegistrationHelper(this);
    }
    return reg;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail() {
    if (mail == null) {
      mail = new MailHelper(this);
    }
    return mail;
  }

  public HttpSession newSession() {
    if (http == null)
      http = new HttpSession(this);
    return http;
  }

  public JamesHelper james() {
    if (james == null) {
      james = new JamesHelper(this);
    }
    return james;
  }

  public UserHelper user() {
    if (user == null) {
      user = new UserHelper(this);
    }
    return user;
  }

  public DbHelper db() {
    if (db == null) {
      db = new DbHelper(this);
    }
    return db;
  }

  public LoginHelper login() {
    if (login == null) {
      login = new LoginHelper(this);
    }
    return login;
  }

  public NavigationHelper goTo() {
    if (nav == null) {
      nav = new NavigationHelper(this);
    }
    return nav;
  }

  public SoapHelper soap() {
    if (soap == null) {
      soap = new SoapHelper(this);
    }
    return soap;
  }
}