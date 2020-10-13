package ru.stqa.pft.addressbook.tests.contacts.withgroups;

import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;

public class ContactFromGroupRemovalTests extends TestBase {

  private ContactData contact;
  private GroupData group;

  @BeforeMethod
  public void ensurePreconditions() {

    var groupData = new GroupData()
            .withId(app.group().id())
            .withName("Looney Tunes");

    var contactData = new ContactData()
            .withId(app.contact().id())
            .withFirstName("Bunny")
            .withLastName("Bugs");

    var contacts = app.db().contacts();
    var groups = app.db().groups();

    // Проверяем наличие контактов (что в приложении есть хотя бы один контакт)
    // Если нет, то создаем через бд (с проверкой) и возвращаем
    if (contacts.isEmpty()) {
      app.db().addContact(contactData);
      contact = app.db().contacts().iterator().next();
    }
    // Проверяем наличие групп (что в приложении есть хотя бы одна группа)
    // Если нет, то создаем через бд (с проверкой) и возвращаем
    if (groups.isEmpty()) {
      app.db().addGroup(groupData);
      group = app.db().groups().iterator().next();
    }
    // Проверяем, что существуют контакты, которые можно удалить из группы
    // Если есть, то возвращаем такой контакт и любую группу, в которую он добавлен
    if (!(contacts.isEmpty() && groups.isEmpty())) {
      for (var c : contacts) {
        if (!c.getGroups().isEmpty()) {
          contact = c;
          group = c.getGroups().iterator().next();
          return;
        }
      }
    }
    // Если все контакты удалены из всех групп, то берем любой контакт и добавляем в любую группу через интерфейс
    contact = app.db().contacts().iterator().next();
    group = app.db().groups().iterator().next();
    app.goTo().homePage();
    app.contact().addToGroup(contact, group);
  }

  @Test
  public void testContactGroupDeletion() {
    var before = app.db().contacts();
    var contactWithoutGroup = contact.outOfGroup(group);
    app.goTo().homePage();
    // обновляем страницу, чтоб визуально получить актуальный список контактов
    app.goTo().refreshPage();
    app.contact().removeFromGroup(contact, group);
    var after = app.db().contacts();
    assertThat(after, equalTo(before.without(contact).withAdded(contactWithoutGroup)));
  }
}