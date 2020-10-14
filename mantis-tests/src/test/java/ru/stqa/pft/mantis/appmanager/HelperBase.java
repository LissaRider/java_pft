package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelperBase {

  protected ApplicationManager app;
  protected WebDriver driver;
  private String state;
  private boolean acceptNextAlert = true;

  public HelperBase(ApplicationManager app) {
    this.app = app;
    driver = app.getDriver();
  }

  //<editor-fold desc="Base methods">
  public void open(String url) {
    driver.get(url);
  }

  public WebElement getElement(By by) {
    return driver.findElement(by);
  }

  public List<WebElement> getElements(By by) {
    return driver.findElements(by);
  }

  public void click(By by) {
    getElement(by).click();
  }

  public void clearAndType(By by, String value) {
    click(by);
    if (value != null) {
      String extValue = getElement(by).getAttribute("value");
      if (!value.equals(extValue)) {
        getElement(by).clear();
        getElement(by).sendKeys(value);
      }
    }
  }

  public boolean isElementPresent(By by) {
    try {
      getElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAnyElementPresent(By by) {
    try {
      driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      return getElements(by).size() > 0;
    } finally {
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
  }

  public boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
//      System.out.println(alertText);
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  //</editor-fold>

  //<editor-fold desc="Extensions">
  public void waitForPageLoad() {//
    try {
      WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(app.properties.getProperty("wait.customPageLoad")));
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
    } catch (TimeoutException | NullPointerException exception) {
      //sometimes Page remains in Interactive mode and never becomes Complete,
      // then we can still try to access the controls
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