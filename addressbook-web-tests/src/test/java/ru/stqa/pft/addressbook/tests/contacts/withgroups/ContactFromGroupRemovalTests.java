package ru.stqa.pft.addressbook.tests.contacts.withgroups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    // Если нет, то создаем через бд (с проверкой)
    if (contacts.isEmpty()) {
      app.db().addContact(contactData);
    }
    // Проверяем наличие групп (что в приложении есть хотя бы одна группа)
    // Если нет, то создаем через бд (с проверкой)
    if (groups.isEmpty()) {
      app.db().addGroup(groupData);
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
    // Обновляем страницу, чтоб визуально получить актуальный список контактов
    app.goTo().refreshPage();
    app.contact().addToGroup(contact, group);
  }

  @Test
  public void testContactGroupDeletion() {
    // Hibernate кеширует результаты, поэтому получаем список контактов из бд и обновляем данные по нашему контакту
    var before = app.db().contacts();
    contact = before.stream().filter(c -> (c.getId() == contact.getId())).collect(Collectors.toSet()).iterator().next();

    // Получаем список групп контакта
    var groupsBefore = contact.getGroups();

    // Переходим на страницу с контактами
    app.goTo().homePage();

    // Обновляем страницу, чтоб визуально получить актуальный список контактов
    app.goTo().refreshPage();

    // Удаляем контакт из группы
    app.contact().removeFromGroup(contact, group);

    // Hibernate кеширует результаты, поэтому снова получаем список контактов из бд и обновляем данные по нашему контакту
    var after = app.db().contacts();
    contact = after.stream().filter(c -> (c.getId() == contact.getId())).collect(Collectors.toSet()).iterator().next();

    // Сравниваем размер списков групп контакта до и после удаления
    assertThat(contact.getGroups().size(), equalTo(groupsBefore.size() - 1));

    // Получаем список групп контакта
    var groupsAfter = contact.getGroups();

    // Сравниваем изменившиеся списки групп контакта
    assertThat(groupsAfter, equalTo(groupsBefore.without(group)));
  }
}