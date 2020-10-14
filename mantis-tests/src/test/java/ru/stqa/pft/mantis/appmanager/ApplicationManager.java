package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  public final Properties properties;

  private WebDriver driver;

  private RegistrationHelper reg;
  private FtpHelper ftp;

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
    if (driver == null) {
      if (browser != null && !browser.isEmpty()) {
        switch (browser) {
          case BrowserType.FIREFOX:
            // https://github.com/mozilla/geckodriver/releases
            driver = new FirefoxDriver();
            break;
          case BrowserType.CHROME:
            // https://chromedriver.storage.googleapis.com/85.0.4183.87/chromedriver_win32.zip
            driver = new ChromeDriver();
            break;
          case BrowserType.IE:
            var ieOptions = new InternetExplorerOptions();
            ieOptions.disableNativeEvents();
            // https://selenium-release.storage.googleapis.com/3.150/IEDriverServer_Win32_3.150.1.zip
            driver = new InternetExplorerDriver(ieOptions);
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

  public void loadProperties(String key, String def) throws IOException {
    properties.load(new FileReader(new File(configFile(key, def))));
  }

  public String configFile(String key, String def) {
    return String.format("src/test/resources/config/%s.properties",
            System.getProperty(key, def));
  }

  public void stop() {
    if (driver != null) {
      driver.quit();
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

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}