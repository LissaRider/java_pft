import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GroupCreationTests {
  private WebDriver driver;
  private String baseUrl;

  @BeforeClass(alwaysRun = true)
  public void setUp() {

    driver = new FirefoxDriver();

//    driver = new ChromeDriver();

//    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
//    ieOptions.disableNativeEvents();
//    driver = new InternetExplorerDriver(ieOptions);

    baseUrl = "http://localhost/addressbook";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void testGroupCreation() {
    driver.get(baseUrl);

    driver.findElement(By.name("user")).click();
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("admin");

    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("secret");

    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

    driver.findElement(By.linkText("groups")).click();

    driver.findElement(By.name("new")).click();

    driver.findElement(By.name("group_name")).click();
    driver.findElement(By.name("group_name")).clear();
    driver.findElement(By.name("group_name")).sendKeys("Group1");

    driver.findElement(By.name("group_header")).click();
    driver.findElement(By.name("group_header")).clear();
    driver.findElement(By.name("group_header")).sendKeys("Logo");

    driver.findElement(By.name("group_footer")).click();
    driver.findElement(By.name("group_footer")).clear();
    driver.findElement(By.name("group_footer")).sendKeys("Comment");

    driver.findElement(By.name("submit")).click();

    driver.findElement(By.linkText("group page")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    driver.quit();
  }

//  private boolean isElementPresent(By by) {
//    try {
//      driver.findElement(by);
//      return true;
//    } catch (NoSuchElementException e) {
//      return false;
//    }
//  }
}
