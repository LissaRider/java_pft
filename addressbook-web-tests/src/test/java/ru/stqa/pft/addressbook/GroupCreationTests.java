package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
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

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    baseUrl = "http://localhost/addressbook";
    driver.get(baseUrl);
    driver.findElement(By.name("user")).click();
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("admin");

    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("secret");

    driver.findElement(By.cssSelector("input[type=submit]")).click();
  }

  @Test
  public void testGroupCreation() {

    driver.findElement(By.cssSelector("#nav a[href='group.php']")).click();

//    driver.findElement(By.cssSelector("input[name=new]:nth-child(1)")).click(); /*только верхняя кнопка*/
//    driver.findElement(By.cssSelector("input[name=new]:nth-child(2)")).click(); /*только нижняя кнопка*/
//    driver.findElements(By.name("new")).stream().findFirst().get().click(); /*первая кнопка из найденных*/
    driver.findElements(By.name("new")).stream().findAny().get().click(); /*любая кнопка из найденных*/

    driver.findElement(By.name("group_name")).click();
    driver.findElement(By.name("group_name")).clear();
    driver.findElement(By.name("group_name")).sendKeys("Relatives");
//    driver.findElement(By.name("group_name")).sendKeys("Коллеги");
//    driver.findElement(By.name("group_name")).sendKeys("Друзья");
//    driver.findElement(By.name("group_name")).sendKeys("Знакомые");

    driver.findElement(By.name("group_header")).click();
    driver.findElement(By.name("group_header")).clear();
    driver.findElement(By.name("group_header")).sendKeys("<h1>RELATIVES</h1><p>Created by Lissa Rider</p></p>");

    driver.findElement(By.name("group_footer")).click();
    driver.findElement(By.name("group_footer")).clear();
    driver.findElement(By.name("group_footer")).sendKeys("<a href=\"edit.php\">add contact</a> \n" +
            " <a href=\"group.php?new=New+group\" target=\"_self\">add group</a>");

    driver.findElement(By.name("submit")).click();

    driver.findElement(By.cssSelector("#content a[href='group.php']")).click();
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
