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
    app.contact().verifyPresence(newContact, 2);
  }

  @Test(testName = "Провека удаления первого контакта на главной странице")
  public void testFirstContactDeletionOnHomePage() {
    List<ContactData> before = app.contact().list();
    app.contact().deleteFromList(0);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(0);
    Assert.assertEquals(after, before);
  }

  @Test(testName = "Провека удаления последнего контакта на главной странице")
  public void testLastContactDeletionOnHomePage() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().deleteFromList(index);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), index);
    before.remove(index);
    Assert.assertEquals(before, after);
  }

  @Test(testName = "Проверка удаления контакта со страницы редактирования")
  public void testContactDeletionOnEditContactPage() {
    List<ContactData> before = app.contact().list();
    app.contact().deleteFromEditPage(0);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(0);
    Assert.assertEquals(before, after);
  }

  @Test(testName = "Проверка удаления всех контактов")
  public void testAllContactsDeletion() {
    List<ContactData> before = app.contact().list();
    app.contact().deleteAll();
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), 0);
    before.clear();
    Assert.assertEquals(before, after);
  }
}