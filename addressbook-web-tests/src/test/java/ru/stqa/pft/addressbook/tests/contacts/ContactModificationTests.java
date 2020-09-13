package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  public ContactData newContact = new ContactData("James", "Bond");

  @Test(testName = "Проверка редактирования первого контакта со страницы редактирования")
  public void testFirstContactModificationFromEditContactPage() {
    app.contact().verifyContactPresence(newContact, 3);
    List<ContactData> before = app.contact().getContactsList();
    app.contact().initContactModification(0);
    ContactData contact = new ContactData(before.get(0).getId(),
            "Robin",
            "Batkovich",
            "Hood",
            "Prince of Thieves",
            "robin_hood.jpg",
            "Senior Software Developer",
            "Sherwood Forest",
            "Nottingham",
            "8(495) 222-22-22",
            "8(999) 222-22-22",
            "8(800) 777-77-77",
            "8(800) 777-77-70",
            "robinhood@gmail.com",
            "robin.hood@yandex.ru",
            "robin.hood@mail.ru",
            "www.folktales.com",
            2,
            "September",
            "1991",
            "1",
            "september",
            "2021",
            "England",
            "8(909) 777-77-77",
            "Rise and rise again until lambs become lions."
    );
    app.contact().modifyContact(contact);
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(0);
    before.add(contact);
    Comparator<ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test(testName = "Проверка редактирования последнего контакта со страницы просмотра")
  public void testLastContactModificationFromViewContactPage() {
    app.contact().verifyContactPresence(newContact, 3);
    List<ContactData> before = app.contact().getContactsList();
    app.contact().viewContact(before.size() - 1);
    app.contact().initContactModificationOnViewPage();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),
            "John",
            "Ivanovich",
            "Carter",
            "Virginia",
            "john_carter.jpg",
            "Senior Software Developer",
            "Moscow City",
            "Moscow, metro station Business Center",
            "8(495) 111-11-11",
            "8(999) 111-11-11",
            "8(800) 999-99-99",
            "8(800) 999-99-90",
            "johncarter@gmail.com",
            "john.carter@yandex.ru",
            "john.carter@mail.ru",
            "www.edgarricestories.com",
            24,
            "February",
            "1994",
            "23",
            "february",
            "2024",
            "Moscow,  metro station Vystavochnaya",
            "8(909) 888-88-88",
            "By god... I am on Mars."
    );
    app.contact().modifyContact(contact);
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
