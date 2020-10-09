package ru.stqa.pft.addressbook.tests.contacts.withgroups;

import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;

public class ContactFromGroupRemovalTests extends TestBase {

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
    // TODO создаем новый контакт через базу c привязкой к созданной группе в базе данных
    //  var contact = new ContactData().withId(contactId).withFirstName("Bunny").withLastName("Bugs").inGroup(group);
    var contact = new ContactData().withId(contactId).withFirstName("Bunny").withLastName("Bugs");
    app.db().addContact(contact);

    // переходим на страницу с контактами
    app.goTo().homePage();

    // обновляем страницу, чтоб визуально получить актуальный список контактов
    app.goTo().refreshPage();

    // через интерефейс добавляем созданный контакт в созданную группу (с проверкой через базу)
    app.contact().addToGroup(contact, group);
    assertFalse(app.db().contacts().iterator().next().getGroups().isEmpty());
  }

  @Test
  public void testContactGroupAddition() {
    var before = app.db().contacts();
    var contact = before.iterator().next();
    var group = app.db().groups().iterator().next();
    var contactWithoutGroup = contact.outOfGroup(group);
    app.goTo().homePage();
    app.contact().removeFromGroup(contact, group);
    var after = app.db().contacts();
    assertThat(after, equalTo(before.without(contact).withAdded(contactWithoutGroup)));
  }
}