package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.nav().goToHomePage();
    app.contact().selectAnyContact();
    app.contact().initContactDeletion();
    app.base().closeAlertAndGetItsText();
  }
}