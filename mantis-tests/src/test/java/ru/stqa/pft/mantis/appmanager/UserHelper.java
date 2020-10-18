package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  private final By resetPasswordBtnLoc = By.cssSelector("#manage-user-reset-form input[type=submit][class*=btn]");

  private By link(String username) {
    return By.linkText(username);
  }

  public void resetPassword(String username) {
    click(link(username));
    click(resetPasswordBtnLoc);
  }
}