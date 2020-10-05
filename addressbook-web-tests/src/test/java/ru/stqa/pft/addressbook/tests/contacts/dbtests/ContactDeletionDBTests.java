package ru.stqa.pft.addressbook.tests.contacts.dbtests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionDBTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    ContactData newContact = new ContactData()
            .withFirstName("Peter")
            .withLastName("Pane");
    if (app.db().contacts().size() == 0) app.contact().create(newContact);
  }

  @Test(testName = "Провека удаления контакта на главной странице")
  public void testFContactDeletionFromHomePage() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteFromList(deletedContact);
    app.goTo().homePage();
    assertThat(app.db().contacts().size(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

  @Test(testName = "Проверка удаления контакта со страницы редактирования")
  public void testContactDeletionFromEditContactPage() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteFromEditPage(deletedContact);
    app.goTo().homePage();
    assertThat(app.db().contacts().size(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

  @Test(testName = "Проверка удаления всех контактов")
  public void testAllContactsDeletion() {
    Contacts before = app.db().contacts();
    app.contact().deleteAll();
    app.goTo().homePage();
    assertThat(app.db().contacts().size(), equalTo(0));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.empty()));
  }
}