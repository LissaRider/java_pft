package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test(testName = "Проверка создания контакта")
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Alice")
            .withMiddleName("Batkovna")
            .withLastName("Fabler")
            .withNickname("LisAnieL")
            .withFileSource("dark_alice.jpg")
            .withJobTitle("Middle QA Automation Engineer")
            .withCompanyName("Bank")
            .withMainAddress("Moscow, Chertanovo Tsentralnoye District")
            .withHomePhone("8(495) 000-00-00")
            .withMobilePhone("8(999) 000-00-00")
            .withWorkPhone("8(800) 888-88-88")
            .withFaxNumber("8(800) 888-88-80")
            .withEmail("lissarider@gmail.com")
            .withEmail2("lisaniel.lisaniel@gmail.com")
            .withEmail3("lisaniel@mail.ru")
            .withWebSite("www.fairytales.com")
            .withBirthDay(9)
            .withBirthMonth("March")
            .withBirthYear("1996")
            .withAnniversaryDay("8")
            .withAnniversaryMonth("March")
            .withAnniversaryYear("2026")
            .withAdAddress("Moscow, Biryulyovo Zapadnoye District")
            .withAdPhone("8(909) 999-99-99")
            .withNotes("\"Who in the world am I?\" Ah, that is the great puzzle!");
    app.contact().create(contact);
    Contacts after = app.contact().all();

    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after
            .stream()
            .mapToInt(ContactData::getId)
            .max()
            .orElseThrow())))); /*java 11: getAsInt() to orElseThrow()*/
  }
}