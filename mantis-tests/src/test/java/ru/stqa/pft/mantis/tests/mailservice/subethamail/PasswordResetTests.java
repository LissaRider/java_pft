package ru.stqa.pft.mantis.tests.mailservice.subethamail;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.models.UserData;
import ru.stqa.pft.mantis.tests.TestBase;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class PasswordResetTests extends TestBase {

  UserData user;

  @BeforeMethod
  public void ensurePreconditions() throws IOException, MessagingException {
    var adminLogin = app.getProperty("web.adminLogin");
    var admin = new UserData().withUsername(adminLogin);

    // Получаем из базы список пользователей без админа
    var users = app.db().users().without(admin);

    // Если список пользователей пуст, то регистрируем нового
    if (users.isEmpty()) {
      var now = System.currentTimeMillis();
      user = new UserData()
              .withUsername(String.format("user%s", now))
              .withPassword("password")
              .withEmail(String.format("user%s@localhost", now));
      app.registration().start(user.getUsername(), user.getEmail());
    } else {
      // Если список пользоватей не пуст, то берем любого из списка
      user = users.iterator().next();
    }
  }

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testPasswordReset() throws IOException {
    app.login().loginAsAdmin();
    app.goTo().manageUsersPage();
    app.user().resetPassword(user.getUsername());
    var newPassword = "p@ssw0rd";
    var mailMessages = app.mail().waitForMail(1, 10000);
    var confirmationLink = app.mail().findConfirmationLink(mailMessages, user.getEmail());
    app.registration().finish(confirmationLink, newPassword);
    var session = app.newSession();
    assertTrue(session.login(user.getUsername(), newPassword));
    assertTrue(session.isLoggedInAs(user.getUsername()));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}