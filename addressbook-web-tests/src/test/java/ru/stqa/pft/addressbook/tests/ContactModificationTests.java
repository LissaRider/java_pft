package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;

public class ContactModificationTests extends TestBase {

  @Test(testName = "Проверка редактирования контакта со страницы редактирования")
  public void testContactModificationFromEditContactPage() {
    app.nav().goToHomePage();
    app.contact().initAnyContactModification();
    app.contact().fillContactForm(new ContactData(
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
            "Rise and rise again until lambs become lions."),
            false);
    app.contact().submitContactModification();
    app.contact().returnToHomePage();
  }

  @Test(testName = "Проверка редактирования контакта со страницы просмотра")
  public void testContactModificationFromViewContactPage() {
    app.nav().goToHomePage();
    app.contact().viewAnyContact();
    app.contact().initContactModification();
    app.contact().fillContactForm(new ContactData(
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
            "By god... I am on Mars."),
            false);
    app.contact().submitContactModification();
    app.contact().returnToHomePage();
  }
}
