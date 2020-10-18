package ru.stqa.pft.mantis.tests.mailservice.jamesmail;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.tests.TestBase;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Перед запуском тестов необходимо запустить сам James
 * Для этого перейти в папку с James, затем в bin
 * Открыть консоль cmd и выполнить команду run.bat
 */
public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() throws IOException, javax.mail.MessagingException {
    long now = System.currentTimeMillis();
    var user = String.format("user%s", now);
    var password = "password";
    var email = String.format("user%s@localhost", now);
    app.james().createUser(user, password);
    app.registration().start(user, email);
    var mailMessages = app.james().waitForMail(user, password, 60000);
    var confirmationLink = app.james().findConfirmationLink(mailMessages, email);
    System.out.println(confirmationLink);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }
}