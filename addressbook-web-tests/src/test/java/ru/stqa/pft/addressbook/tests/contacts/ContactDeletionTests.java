package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  public ContactData newContact = new ContactData("Attack", "Clone");

  @Test(testName = "Провека удаления первого контакта на главной странице")
  public void testFirstContactDeletionOnHomePage() {
    app.contact().verifyContactPresence(newContact, 3);
    List<ContactData> before = app.contact().getContactsList();
    app.contact().deleteContactFromList(0);
    app.nav().goToHomePage();
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(0);
    Assert.assertEquals(after, before);
  }

  @Test(testName = "Провека удаления последнего контакта на главной странице")
  public void testLastContactDeletionOnHomePage() {
    app.contact().verifyContactPresence(newContact, 3);
    List<ContactData> before = app.contact().getContactsList();
    app.contact().deleteContactFromList(before.size() - 1);
    app.nav().goToHomePage();
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

  @Test(testName = "Проверка удаления контакта со страницы редактирования")
  public void testContactDeletionOnEditContactPage() {
    app.contact().verifyContactPresence(newContact, 1);
    List<ContactData> before = app.contact().getContactsList();
    app.contact().deleteContactOnEditPage(0);
    app.nav().goToHomePage();
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(0);
    Assert.assertEquals(before, after);
  }

  @Test(testName = "Проверка удаления всех контактов")
  public void testAllContactsDeletion() {
    app.contact().verifyContactPresence(newContact, 3);
    List<ContactData> before = app.contact().getContactsList();
    app.contact().deleteAllContacts();
    app.nav().goToHomePage();
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), 0);
    before.removeAll(before);
    Assert.assertEquals(before, after);
  }
}