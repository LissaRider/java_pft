package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  //<editor-fold desc="Locators">
  private final By usernameLoc = By.id("username");
  private final By emailLoc = By.id("email-field");
  // для кнопки Signup value не используется, так как может меняться в зависимости от локализации
  private final By signupBtnLoc = By.cssSelector("input[type=submit][class*=btn-success]");
  private final By passwordLoc = By.id("password");
  private final By passwordConfirmLoc = By.id("password-confirm");
  private final By submitBtnLoc = By.cssSelector("[class=submit-button] > button");
  //</editor-fold>

  //<editor-fold desc="Methods">
  public void start(String username, String email) {
    app.goTo().signupPage();
    clearAndType(usernameLoc, username);
    clearAndType(emailLoc, email);
    click(signupBtnLoc);
  }

  public void finish(String confirmationLink, String password) {
    open(confirmationLink);
    clearAndType(passwordLoc, password);
    clearAndType(passwordConfirmLoc, password);
    click(submitBtnLoc);
  }
  //</editor-fold>
}