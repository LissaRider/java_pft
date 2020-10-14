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
  //</editor-fold>

  //<editor-fold desc="Methods">
  public void start(String username, String email) {
    open(app.getProperty("web.baseUrl") + "/signup_page.php");
    clearAndType(usernameLoc, username);
    clearAndType(emailLoc, email);
    click(signupBtnLoc);
  }
  //</editor-fold>
}