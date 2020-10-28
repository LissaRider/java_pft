package ru.stqa.pft.addressbook.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.stqa.pft.addressbook.models.LoginData;

public class LoginHelper extends HelperBase {

  //<editor-fold desc="Locators">
  public By passwordLoc = By.name("pass");
  public By usernameLoc = By.name("user");
  public By loginBtnLoc = By.cssSelector("input[type=submit]");
  //</editor-fold>

  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  //<editor-fold desc="Methods">
  @Step("Я авторизуюсь в систему")
  public void login(LoginData loginData) {
    fillLoginForm(loginData.getUsername(), loginData.getPassword());
    submitLogin();
  }

  @Step("Я заполняю поля авторизации")
  public void fillLoginForm(String username, String password) {
    clearAndType(usernameLoc, username);
    clearAndType(passwordLoc, password);
  }

  @Step("Я нажимаю кнопку входа")
  public void submitLogin() {
    click(loginBtnLoc);
  }
  //</editor-fold>
}