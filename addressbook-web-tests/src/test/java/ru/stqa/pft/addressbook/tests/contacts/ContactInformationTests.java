package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTests extends TestBase {

  public static String cleaned(String phone) {
    return phone
            .replaceAll("\\s", "")
            .replaceAll("[-()]", "");
  }

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() != 0) app.contact().deleteAll();
    ContactData newContact = new ContactData()
            .withFirstName("Kenobi")
            .withLastName("Obi-Wan")
            .withMainAddress("Korusant")
            .withHomePhone("+7 (495) 3333333")
            .withMobilePhone("89993333333")
            .withWorkPhone("8(800) 666 66 66")
            .withAdPhone("8(800) 555-55-55")
            .withEmail("kenobiobiwan@gmail.com")
            .withEmail2("kenobi.obiwan@yandex.ru")
            .withEmail3("kenobi.obiwan@mail.ru");
    app.contact().create(newContact);
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getMainAddress(), equalTo(contactInfoFromEditForm.getMainAddress()));
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getAdPhone())
            .stream()
            .filter(s -> !s.equals(""))
            .map(ContactInformationTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream()
            .filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}