package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.util.Collection;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public void addContact(ContactData contact) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(contact);
    session.getTransaction().commit();
    session.close();
    Contacts contacts = contacts();
    assertTrue(!contacts.isEmpty() && contacts.contains(contact));
  }

  public void addGroup(GroupData group) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(group);
    session.getTransaction().commit();
    session.close();
    var groups = groups();
    assertTrue(!groups.isEmpty() && groups.contains(group));
  }
}