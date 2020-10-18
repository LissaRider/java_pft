package ru.stqa.pft.mantis.tests.mailservice.jamesmail;

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
      var username = String.format("user%s", now);
      var password = "password";
      var email = String.format("user%s@localhost", now);
      app.james().createUser(username, password);
      app.registration().start(username, email);
      var mailMessages = app.james().waitForMail(username, password, 60000);
      var confirmationLink = app.james().findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      user = new UserData().withUsername(username).withPassword(password).withEmail(email);
    } else {
      // Если список пользоватей не пуст, то берем любого из списка
      user = users.iterator().next();
    }
  }

  @Test
  public void testPasswordReset() throws IOException, MessagingException {
    String password = "p@ssw0rd";

    app.login().loginAsAdmin();
    app.goTo().manageUsersPage();

    // Пароль может быть изменен перед этим тестом другим тестом
    // Пересоздаем этого юзера с новым паролем
    if (app.james().doesUserExist(user.getUsername())) {
      app.james().deleteUser(user.getUsername());
    }
    app.james().createUser(user.getUsername(), password);

    // Удаляем все предыдущие сообщения у юзера
    var oldMailMessages = app.james().getAllMail(user.getUsername(), password);
    if (!oldMailMessages.isEmpty()) app.james().drainEmail(user.getUsername(), password);

    app.user().resetPassword(user.getUsername());
    var mailMessages = app.james().waitForMail(user.getUsername(), password, 60000);
    var confirmationLink = app.james().findConfirmationLink(mailMessages, user.getEmail());
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user.getUsername(), password));
  }
}