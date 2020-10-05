package ru.stqa.pft.addressbook.models;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<>(contacts.delegate);
  }

  public Contacts(Collection<ContactData> contact) {
    this.delegate = new HashSet<>(contact);
  }

  public Contacts() {
    this.delegate = new HashSet<>();
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

  public Contacts empty() {
    Contacts contacts = new Contacts(this);
    contacts.clear();
    return contacts;
  }
}