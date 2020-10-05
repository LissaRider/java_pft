package ru.stqa.pft.addressbook.tests.contacts.dbtests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationDBTests extends TestBase {

  @BeforeMethod
  public void ensureDBPreconditions() {
    app.goTo().homePage();
    ContactData newContact = new ContactData()
            .withFirstName("James")
            .withLastName("Bond");
    if (app.db().contacts().size() == 0) app.contact().create(newContact);
  }

  @Test(testName = "Проверка редактирования контакта со страницы редактирования (БД)")
  public void dBTestContactModificationFromEditContactPage() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    app.contact().initModification(modifiedContact.getId());
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("Robin")
            .withMiddleName("Batkovich")
            .withLastName("Hood")
            .withNickname("Prince of Thieves")
            .withPhoto(new File("src/test/resources/photos/robin_hood.jpg"))
            .withCompanyName("Sherwood Forest")
            .withJobTitle("Senior Software Developer")
            .withMainAddress("Nottingham")
            .withHomePhone("8(495) 222-22-22")
            .withMobilePhone("8(999) 222-22-22")
            .withWorkPhone("8(800) 777-77-77")
            .withFaxNumber("8(800) 777-77-70")
            .withEmail("robinhood@gmail.com")
            .withEmail2("robin.hood@yandex.ru")
            .withEmail3("robin.hood@mail.ru")
            .withWebSite("www.folktales.com")
            .withBirthDay(2)
            .withBirthMonth("September")
            .withBirthYear("1991")
            .withAnniversaryDay("1")
            .withAnniversaryMonth("september")
            .withAnniversaryYear("2021")
            .withAdAddress("England")
            .withAdPhone("8(909) 777-77-77")
            .withNotes("Rise and rise again until lambs become lions.");
    app.contact().modify(contact);
    assertThat(app.db().contacts().size(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }

  @Test(testName = "Проверка редактирования последнего контакта со страницы просмотра (БД)")
  public void dBTestContactModificationFromViewContactPage() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    app.contact().view(modifiedContact.getId());
    app.contact().initModificationFromViewPage();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("John")
            .withMiddleName("Ivanovich")
            .withLastName("Carter")
            .withNickname("Virginia")
            .withPhoto(new File("src/test/resources/photos/john_carter.jpg"))
            .withCompanyName("Moscow City")
            .withJobTitle("Middle Software Developer")
            .withMainAddress("Moscow, metro station Business Center")
            .withHomePhone("8(495) 111-11-11")
            .withMobilePhone("8(999) 111-11-11")
            .withWorkPhone("8(800) 999-99-99")
            .withFaxNumber("8(800) 999-99-90")
            .withEmail("johncarter@gmail.com")
            .withEmail2("john.carter@yandex.ru")
            .withEmail3("john.carter@mail.ru")
            .withWebSite("www.edgarricestories.com")
            .withBirthDay(24)
            .withBirthMonth("February")
            .withBirthYear("1994")
            .withAnniversaryDay("23")
            .withAnniversaryMonth("february")
            .withAnniversaryYear("2024")
            .withAdAddress("Moscow,  metro station Vystavochnaya")
            .withAdPhone("8(909) 888-88-88")
            .withNotes("By god... I am on Mars.");
    app.contact().modify(contact);
    assertThat(app.db().contacts().size(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
