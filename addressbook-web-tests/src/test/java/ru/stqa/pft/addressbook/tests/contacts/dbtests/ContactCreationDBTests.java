package ru.stqa.pft.addressbook.tests.contacts.dbtests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationDBTests extends TestBase {

  @Test(testName = "Проверка создания контакта (БД)")
  public void dBTestContactCreation() {
    GroupData group = new GroupData()
            .withId(app.group().id())
            .withName("Relatives");
    if (app.db().groups().isEmpty()) app.db().addGroup(group);
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData contact = new ContactData()
            .withFirstName("Alice")
            .withMiddleName("Batkovna")
            .withLastName("Fabler")
            .withNickname("LisAnieL")
            .withPhoto(new File("src/test/resources/photos/dark_alice.jpg"))
            .withCompanyName("Bank")
            .withJobTitle("Middle QA Automation Engineer")
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
            .withNotes("\"Who in the world am I?\" Ah, that is the great puzzle!")
            .inGroup(groups.iterator().next());
    app.contact().create(contact);
    assertThat(app.db().contacts().size(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(contact.withId(after
            .stream()
            .mapToInt(ContactData::getId)
            .max()
            .orElseThrow())))); /*java 11: getAsInt() to orElseThrow()*/
    verifyContactListInUI();
  }

  @Test(testName = "Проверка создания некорректного контакта (БД)")
  public void dBTestBadContactCreation() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData contact = new ContactData()
            .withFirstName("Alice'")
            .withLastName("Fabler");
    app.contact().create(contact);
    assertThat(app.db().contacts().size(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before));
    verifyContactListInUI();
  }
}