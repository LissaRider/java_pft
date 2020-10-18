package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.models.LoginData;

public class LoginHelper extends HelperBase {

  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  //<editor-fold desc="Locators">
  private final By usernameLoc = By.id("username");
  private final By passwordLoc = By.id("password");
  private final By submitBtnLoc = By.cssSelector("input[type=submit][class*=btn-success]");
  //</editor-fold>

  //<editor-fold desc="Methods">
  public void loginAsAdmin() {
    var adminLogin = app.getProperty("web.adminLogin");
    var loginPassword = app.getProperty("web.adminPassword");
    login(new LoginData().withUsername(adminLogin).withPassword(loginPassword));
  }

  public void login(LoginData login) {
    app.goTo().loginPage();
    clearAndType(usernameLoc, login.getUsername());
    click(submitBtnLoc);
    clearAndType(passwordLoc, login.getPassword());
    click(submitBtnLoc);
  }
  //</editor-fold>
}
