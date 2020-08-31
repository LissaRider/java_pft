package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test(testName = "Провека удаления контакта на главной странице", priority=1)
  public void testContactDeletionOnHomePage() {
    app.nav().goToHomePage();
    app.contact().selectAnyContact();
    app.contact().initContactDeletionOnHomePage();
    app.base().closeAlertAndGetItsText();
  }

  @Test(testName = "Проверка удаления контакта со страницы редактирования", priority=2)
  public void testContactDeletionOnEditContactPage() {
    app.nav().goToHomePage();
    app.contact().initAnyContactModification();
    app.contact().initContactDeletionOnEditContactPage();
  }

  @Test(testName = "Проверка удаления всех контактов", priority=3)
  public void testAllContactsDeletion() {
    app.nav().goToHomePage();
    app.contact().selectAllContacts();
    app.contact().initContactDeletionOnHomePage();
    app.base().closeAlertAndGetItsText();
  }
}