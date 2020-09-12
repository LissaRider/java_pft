package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactDeletionTests extends TestBase {

  public ContactData newContact = new ContactData(
          "Clone",
          null,
          "Attack",
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null
  );

  @Test(testName = "Провека удаления контакта на главной странице", priority = 1)
  public void testContactDeletionOnHomePage() {
    app.contact().verifyContactPresence(newContact);
    app.contact().deleteAnyContactFromList();
  }

  @Test(testName = "Проверка удаления контакта со страницы редактирования", priority = 2)
  public void testContactDeletionOnEditContactPage() {
    app.contact().verifyContactPresence(newContact);
    app.contact().deleteAnyContactOnEditPage();
  }

  @Test(testName = "Проверка удаления всех контактов", priority = 3)
  public void testAllContactsDeletion() {
    app.nav().goToHomePage();
    if (!app.contact().isAnyContactPresent()) app.contact().createContacts(newContact, 3);
    app.contact().deleteAllContacts();
  }
}