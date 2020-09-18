package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    ContactData newContact = new ContactData("James", "Bond");
    app.contact().verifyPresence(newContact, 2);
  }

  @Test(testName = "Проверка редактирования первого контакта со страницы редактирования")
  public void testFirstContactModificationFromEditContactPage() {
    List<ContactData> before = app.contact().list();
    app.contact().initModification(0);
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
    app.contact().modify(contact);
    List<ContactData> after = app.contact().list();

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
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().view(index);
    app.contact().initModificationFromViewPage();
    ContactData contact = new ContactData(before.get(index).getId(),
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
    app.contact().modify(contact);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(contact);
    Comparator<ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
