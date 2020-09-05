package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.models.LoginData;

public class LoginHelper extends HelperBase {

  //<editor-fold desc="Locators">
  public By passwordLoc = By.name("pass");
  public By usernameLoc = By.name("user");
  public By loginBtnLoc = By.cssSelector("input[type=submit]");
  //</editor-fold>

  public LoginHelper(WebDriver driver) {
    super(driver);
  }

  //<editor-fold desc="Methods">
  public void login(LoginData loginData) {
    fillLoginForm(loginData.getUsername(), loginData.getPassword());
    submitLogin();
  }

  public void fillLoginForm(String username, String password) {
    clearAndType(usernameLoc, username);
    clearAndType(passwordLoc, password);
  }

  public void submitLogin() {
    click(loginBtnLoc);
  }
  //</editor-fold>
}