package ru.stqa.pft.addressbook.tests.contacts.dataproviders;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCSVDataTests extends TestBase {

  @Test(testName = "RU Проверка создания контакта (CSV)",
          dataProvider = "contactsRUFromCsvFile", dataProviderClass = ContactDataProviders.class)
  public void paramTestRUContactFromCSVCreation(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after
            .stream()
            .mapToInt(ContactData::getId)
            .max()
            .orElseThrow())))); /*java 11: getAsInt() to orElseThrow()*/
  }

  @Test(testName = "EN Проверка создания контакта (CSV)",
          dataProvider = "contactsENFromCsvFile", dataProviderClass = ContactDataProviders.class)
  public void paramTestENContactFromCSVCreation(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after
            .stream()
            .mapToInt(ContactData::getId)
            .max()
            .orElseThrow())))); /*java 11: getAsInt() to orElseThrow()*/
  }
}