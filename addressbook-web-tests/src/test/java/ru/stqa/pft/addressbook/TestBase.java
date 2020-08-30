package ru.stqa.pft.addressbook;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager();

  @BeforeClass(alwaysRun = true)
  public void setUp() {
    app.init();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }
}
