package ru.stqa.pft.addressbook.tests.contacts.withgroups;

import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupAddingTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // удаляем группы и контакты через базу (с проверкой)
    app.db().clearData();

    // создаем новую группу через базу (с проверкой)
    int groupId = new Faker().number().numberBetween(1, 10);
    var group = new GroupData().withId(groupId).withName("Looney Tunes");
    app.db().addGroup(group);

    // создаем новый контакт через базу (с проверкой)
    int contactId = new Faker().number().numberBetween(1, 10);
    var contact = new ContactData().withId(contactId).withFirstName("Bunny").withLastName("Bugs");
    app.db().addContact(contact);
  }

  @Test
  public void testContactGroupAddition() {
    var before = app.db().contacts();
    var contact = before.iterator().next();
    var group = app.db().groups().iterator().next();
    var contactWithAddedGroup = contact.inGroup(group);
    app.goTo().homePage();
    // обновляем страницу, чтоб визуально получить актуальный список контактов
    app.goTo().refreshPage();
    app.contact().addToGroup(contact, group);
    var after = app.db().contacts();
    assertThat(after, equalTo(before.without(contact).withAdded(contactWithAddedGroup)));
  }
}
