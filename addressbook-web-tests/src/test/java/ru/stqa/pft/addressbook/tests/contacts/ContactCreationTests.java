package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(testName = "Проверка создания контакта")
  public void testContactCreation() {
    app.goTo().goToHomePage();
    List<ContactData> before = app.contact().getContactsList();
    ContactData contact = new ContactData(
            "Alice",
            "Batkovna",
            "Fabler",
            "LisAnieL",
            "dark_alice.jpg",
            "Middle QA Automation Engineer",
            "Bank",
            "Moscow, Chertanovo Tsentralnoye District",
            "8(495) 000-00-00",
            "8(999) 000-00-00",
            "8(800) 888-88-88",
            "8(800) 888-88-80",
            "lissarider@gmail.com",
            "lisaniel.lisaniel@gmail.com",
            "lisaniel@mail.ru",
            "www.fairytales.com",
            9,
            "March",
            "1996",
            "8",
            "March",
            "2026",
            "Moscow, Biryulyovo Zapadnoye District",
            "8(909) 999-99-99",
            "\"Who in the world am I?\" Ah, that is the great puzzle!"
    );
    app.contact().createContact(contact);
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Comparator<ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}