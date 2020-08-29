package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests {
  private WebDriver driver;
  private String baseUrl;

  @BeforeClass(alwaysRun = true)
  public void setUp() {
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testContactCreation() {
    driver.get("https://localhost/addressbook");
    driver.findElement(By.name("user")).click();
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.linkText("add new")).click();
    driver.findElement(By.name("firstname")).click();
    driver.findElement(By.name("firstname")).clear();
    driver.findElement(By.name("firstname")).sendKeys("Alice");
    driver.findElement(By.name("middlename")).click();
    driver.findElement(By.name("middlename")).clear();
    driver.findElement(By.name("middlename")).sendKeys("Batkovna");
    driver.findElement(By.name("lastname")).click();
    driver.findElement(By.name("lastname")).clear();
    driver.findElement(By.name("lastname")).sendKeys("Fabler");
    driver.findElement(By.name("nickname")).click();
    driver.findElement(By.name("nickname")).clear();
    driver.findElement(By.name("nickname")).sendKeys("LisAnieL");
    driver.findElement(By.name("photo")).click();
    driver.findElement(By.name("photo")).clear();
    driver.findElement(By.name("photo")).sendKeys("C:\\fakepath\\dark_alice.jpg");
    driver.findElement(By.name("title")).click();
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("Middle QA Automation Engineer");
    driver.findElement(By.name("company")).click();
    driver.findElement(By.name("company")).clear();
    driver.findElement(By.name("company")).sendKeys("Bank");
    driver.findElement(By.name("address")).click();
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys("Moscow, Chertanovo Tsentralnoye District");
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.name("home")).click();
    driver.findElement(By.name("home")).clear();
    driver.findElement(By.name("home")).sendKeys("8(495) 000-00-00");
    driver.findElement(By.name("mobile")).click();
    driver.findElement(By.name("mobile")).clear();
    driver.findElement(By.name("mobile")).sendKeys("8(999) 000-00-00");
    driver.findElement(By.name("work")).click();
    driver.findElement(By.name("work")).clear();
    driver.findElement(By.name("work")).sendKeys("8(800) 888-88-88");
    driver.findElement(By.name("fax")).click();
    driver.findElement(By.name("fax")).clear();
    driver.findElement(By.name("fax")).sendKeys("8(800) 888-88-80");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("lissarider@gmail.com");
    driver.findElement(By.name("email2")).click();
    driver.findElement(By.name("email2")).clear();
    driver.findElement(By.name("email2")).sendKeys("lisaniel.lisaniel@gmail.com");
    driver.findElement(By.name("email3")).click();
    driver.findElement(By.name("email3")).clear();
    driver.findElement(By.name("email3")).sendKeys("lisaniel@mail.ru");
    driver.findElement(By.name("homepage")).click();
    driver.findElement(By.name("homepage")).clear();
    driver.findElement(By.name("homepage")).sendKeys("www.fairytales.com");
    driver.findElement(By.name("bday")).click();
    new Select(driver.findElement(By.name("bday"))).selectByVisibleText("8");
    driver.findElement(By.name("bday")).click();
    driver.findElement(By.name("bmonth")).click();
    new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText("March");
    driver.findElement(By.name("bmonth")).click();
    driver.findElement(By.name("byear")).click();
    driver.findElement(By.name("byear")).clear();
    driver.findElement(By.name("byear")).sendKeys("1996");
    driver.findElement(By.name("aday")).click();
    new Select(driver.findElement(By.name("aday"))).selectByVisibleText("8");
    driver.findElement(By.name("aday")).click();
    driver.findElement(By.name("amonth")).click();
    new Select(driver.findElement(By.name("amonth"))).selectByVisibleText("March");
    driver.findElement(By.name("amonth")).click();
    driver.findElement(By.name("ayear")).click();
    driver.findElement(By.name("ayear")).clear();
    driver.findElement(By.name("ayear")).sendKeys("2026");
    driver.findElement(By.name("new_group")).click();
    new Select(driver.findElement(By.name("new_group"))).selectByVisibleText("Relatives");
    driver.findElement(By.name("new_group")).click();
    driver.findElement(By.name("address2")).click();
    driver.findElement(By.name("address2")).clear();
    driver.findElement(By.name("address2")).sendKeys("Moscow, Biryulyovo Zapadnoye District");
    driver.findElement(By.name("phone2")).click();
    driver.findElement(By.name("phone2")).clear();
    driver.findElement(By.name("phone2")).sendKeys("8(909) 999-99-99");
    driver.findElement(By.name("notes")).click();
    driver.findElement(By.name("notes")).clear();
    driver.findElement(By.name("notes")).sendKeys("\"'Who in the world am I?' Ah, that's the great puzzle!\"");
    driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    driver.findElement(By.linkText("home page")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() { driver.quit(); }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}