package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws IOException {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }
}