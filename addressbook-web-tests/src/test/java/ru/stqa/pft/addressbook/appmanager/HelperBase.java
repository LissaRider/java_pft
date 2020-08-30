package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HelperBase {

  protected WebDriver driver;
  private String state;
  private boolean acceptNextAlert = true;

  public HelperBase(WebDriver driver) {
    this.driver = driver;
  }

  //<editor-fold desc="Base methods">
  public WebElement getElement(By by) {
    return driver.findElement(by);
  }

  public List<WebElement> getElements(By by) {
    return driver.findElements(by);
  }

  public WebElement getAnyElement(By by) {
    return getElements(by).stream().findAny().orElse(null);
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
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  //</editor-fold>

  //<editor-fold desc="Extensions">
  public void WaitForPageLoad(int timeOutInSeconds) {//
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
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