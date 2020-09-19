package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  public static String cleaned(String phone) {
    return phone
            .replaceAll("\\s", "")
            .replaceAll("[-()]", "");
  }

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    app.contact().deleteAll();
    ContactData newContact = new ContactData()
            .withFirstName("Kenobi")
            .withLastName("Obi-Wan")
            .withHomePhone("+7 (495) 3333333")
            .withMobilePhone("89993333333")
            .withWorkPhone("8(800) 666 66 66")
            .withAdPhone("8(800) 555-55-55");
    app.contact().create(newContact);
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
//    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
//    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
//    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
//    assertThat(contact.getAdPhone(), equalTo(cleaned(contactInfoFromEditForm.getAdPhone())));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getAdPhone())
            .stream()
            .filter(s -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }
}