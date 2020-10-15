package ru.stqa.pft.addressbook.tests.contacts.withgroups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupAddingTests extends TestBase {

  ContactData contact;
  GroupData group;

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
    // Если нет, то создаем через бд (с проверкой)
    if (contacts.isEmpty()) {
      app.db().addContact(contactData);
    }
    // Проверяем наличие групп (что в приложении есть хотя бы одна группа)
    // Если нет, то создаем через бд (с проверкой) и возвращаем
    if (groups.isEmpty()) {
      app.db().addGroup(groupData);
      group = app.db().groups().iterator().next();
    }
    // Проверяем, что существуют контакты, которые можно добавить в группу
    // Если да, то возвращаем контакт с группой
    if (!(contacts.isEmpty() && groups.isEmpty())) {
      for (var g : groups) {
        for (var c : contacts) {
          if (!c.getGroups().contains(g)) {
            contact = c;
            group = g;
            return;
          }
        }
      }
      // Если все контакты добавлены во все группы, берем любой контакт и создаем новую группу через бд (с проверкой)
      contact = app.db().contacts().iterator().next();
      group = groupData;
      app.db().addGroup(group);
    }
  }

  @Test
  public void testContactGroupAddition() {
    // Hibernate кеширует результаты, поэтому получаем список контактов из бд и обновляем данные по нашему контакту
    var before = app.db().contacts();
    contact = before.stream().filter(c -> (c.getId() == contact.getId())).collect(Collectors.toSet()).iterator().next();

    // Получаем список групп контакта
    var groupsBefore = contact.getGroups();

    // Переходим на страницу с контактами
    app.goTo().homePage();

    // Обновляем страницу, чтоб визуально получить актуальный список контактов
    app.goTo().refreshPage();

    // Добавляем контакт в группу
    app.contact().addToGroup(contact, group);

    // Hibernate кеширует результаты, поэтому снова получаем список контактов из бд и обновляем данные по нашему контакту
    var after = app.db().contacts();
    contact = after.stream().filter(c -> (c.getId() == contact.getId())).collect(Collectors.toSet()).iterator().next();

    // Сравниваем размер списков групп контакта до и после добавления
    assertThat(contact.getGroups().size(), equalTo(groupsBefore.size() + 1));

    // Получаем список групп контакта
    var groupsAfter = contact.getGroups();

    // Сравниваем изменившиеся списки групп контакта
    assertThat(groupsAfter, equalTo(groupsBefore.withAdded(group)));
  }
}