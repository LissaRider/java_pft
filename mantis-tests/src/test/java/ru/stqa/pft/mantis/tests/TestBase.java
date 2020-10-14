package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws IOException {
    app.init();
    // в последней версии mantis config_inc.php находится в папке config
    app.ftp().upload(new File("src/test/resources/config/config_inc.php"),
            "config/config_inc.php", "config/config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
    app.stop();
  }
}