package ru.stqa.pft.addressbook;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase extends ApplicationManager {

  @BeforeClass(alwaysRun = true)
  public void setUp() {
    init();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    stop();
  }
}
