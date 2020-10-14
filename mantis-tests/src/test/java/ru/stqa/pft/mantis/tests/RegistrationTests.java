package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() throws IOException {
    app.registration().start("user1", "user1@localhost.localdomain");
  }
}