package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

  private String browser = BrowserType.FIREFOX;
//  private String browser = BrowserType.CHROME;
//  private String browser = BrowserType.IE;

  protected final ApplicationManager app = new ApplicationManager(browser);

  @BeforeClass(alwaysRun = true)
  public void setUp() {
    app.init();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }
}