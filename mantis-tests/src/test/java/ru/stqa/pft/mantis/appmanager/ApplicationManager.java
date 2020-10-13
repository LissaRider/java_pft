package ru.stqa.pft.appmanager;

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

  WebDriver driver;

  public ApplicationManager() {
    properties = new Properties();
  }

  public void init() throws IOException {
    loadProperties("target", "local");
    loadProperties("timeout", "timeout");
    String browser = properties.getProperty("web.browser");
    if (browser != null && !browser.isEmpty()) {
      switch (browser) {
        case BrowserType.FIREFOX:
          driver = new FirefoxDriver(); // https://github.com/mozilla/geckodriver/releases
          break;
        case BrowserType.CHROME:
          driver = new ChromeDriver(); // https://chromedriver.storage.googleapis.com/85.0.4183.87/chromedriver_win32.zip
          break;
        case BrowserType.IE:
          InternetExplorerOptions ieOptions = new InternetExplorerOptions();
          ieOptions.disableNativeEvents();
          driver = new InternetExplorerDriver(ieOptions); // https://selenium-release.storage.googleapis.com/3.150/IEDriverServer_Win32_3.150.1.zip
          break;
        default:
          throw new IOException("Unrecognized browser: " + browser);
      }
    } else {
      throw new WebDriverException("Property 'web.browser' is null or not set (" + configFile("target", "local") + ")");
    }

    driver.manage().timeouts().implicitlyWait(
            Long.parseLong(properties.getProperty("wait.implicitly")), TimeUnit.SECONDS);

    String baseUrl = properties.getProperty("web.baseUrl");
    driver.get(baseUrl);
  }

  public void loadProperties(String key, String def) throws IOException {
    properties.load(new FileReader(new File(configFile(key, def))));
  }

  public String configFile(String key, String def) {
    return String.format("src/test/resources/config/%s.properties",
            System.getProperty(key, def));
  }

  public void stop() {
    driver.quit();
  }
}