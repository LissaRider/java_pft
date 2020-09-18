package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    ContactData newContact = new ContactData("Attack", "Clone");
    app.contact().verifyContactPresence(newContact, 2);
  }

  @Test(testName = "Провека удаления первого контакта на главной странице")
  public void testFirstContactDeletionOnHomePage() {
    List<ContactData> before = app.contact().getContactsList();
    app.contact().deleteContactFromList(0);
    app.goTo().goToHomePage();
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(0);
    Assert.assertEquals(after, before);
  }

  @Test(testName = "Провека удаления последнего контакта на главной странице")
  public void testLastContactDeletionOnHomePage() {
    List<ContactData> before = app.contact().getContactsList();
    app.contact().deleteContactFromList(before.size() - 1);
    app.goTo().goToHomePage();
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

  @Test(testName = "Проверка удаления контакта со страницы редактирования")
  public void testContactDeletionOnEditContactPage() {
    List<ContactData> before = app.contact().getContactsList();
    app.contact().deleteContactOnEditPage(0);
    app.goTo().goToHomePage();
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(0);
    Assert.assertEquals(before, after);
  }

  @Test(testName = "Проверка удаления всех контактов")
  public void testAllContactsDeletion() {
    List<ContactData> before = app.contact().getContactsList();
    app.contact().deleteAllContacts();
    app.goTo().goToHomePage();
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), 0);
    before.removeAll(before);
    Assert.assertEquals(before, after);
  }
}