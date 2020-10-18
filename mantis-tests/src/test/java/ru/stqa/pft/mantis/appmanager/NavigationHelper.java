package ru.stqa.pft.mantis.appmanager;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  private final String baseUrl = app.getProperty("web.baseUrl");

  public void signupPage() {
    open(baseUrl + "/signup_page.php");
  }

  public void loginPage() {
    open(baseUrl + "/login_page.php");
  }

  public void manageUsersPage() {
    open(baseUrl + "/manage_user_page.php");
  }
}