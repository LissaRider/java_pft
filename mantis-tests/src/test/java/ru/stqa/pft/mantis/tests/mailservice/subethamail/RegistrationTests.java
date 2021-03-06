package ru.stqa.pft.mantis.tests.mailservice.subethamail;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.tests.TestBase;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException {
    var now = System.currentTimeMillis();
    var user = String.format("user%s", now);
    var password = "password";
    var email = String.format("user%s@localhost", now);
    app.registration().start(user, email);
    var mailMessages = app.mail().waitForMail(2, 10000);
    var confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}