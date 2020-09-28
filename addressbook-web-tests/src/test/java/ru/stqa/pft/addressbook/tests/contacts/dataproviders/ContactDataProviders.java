package ru.stqa.pft.addressbook.tests.contacts.dataproviders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import ru.stqa.pft.addressbook.models.ContactData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ContactDataProviders {

  @DataProvider
  public Iterator<Object[]> fakeContactsRU() {
    List<Object[]> list = new ArrayList<>();
    for (int i = 1; i < 4; i++) {
      list.add(new Object[]{ getFakeContact("ru") });
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> fakeContactsEN() {
    List<Object[]> list = new ArrayList<>();
    for (int i = 1; i < 4; i++) {
      list.add(new Object[]{getFakeContact("en")});
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> contactsRUFromCsvFile() throws IOException {
    return getDataFromCSVFile("src/test/resources/data/contacts/contacts_ru.csv");
  }

  @DataProvider
  public Iterator<Object[]> contactsENFromCsvFile() throws IOException {
    return getDataFromCSVFile("src/test/resources/data/contacts/contacts_en.csv");
  }

  @DataProvider
  public Iterator<Object[]> contactsRUFromXmlFile() throws IOException {
    return getDataFromXmlFile("src/test/resources/data/contacts/contacts_ru.xml");
  }

  @DataProvider
  public Iterator<Object[]> contactsENFromXmlFile() throws IOException {
    return getDataFromXmlFile("src/test/resources/data/contacts/contacts_en.xml");
  }

  @DataProvider
  public Iterator<Object[]> contactsRUFromJsonFile() throws IOException {
    return getDataFromJsonFile("src/test/resources/data/contacts/contacts_ru.json");
  }

  @DataProvider
  public Iterator<Object[]> contactsENFromJsonFile() throws IOException {
    return getDataFromJsonFile("src/test/resources/data/contacts/contacts_en.json");
  }

  @DataProvider
  public Iterator<Object[]> contactsRUFromYamlFile() throws IOException {
    return getDataFromYamlFile("src/test/resources/data/contacts/contacts_ru.yaml");
  }

  @DataProvider
  public Iterator<Object[]> contactsENFromYamlFile() throws IOException {
    return getDataFromYamlFile("src/test/resources/data/contacts/contacts_en.yaml");
  }

  private ContactData getFakeContact(String lang) {
    Faker fake = new Faker(new Locale(lang));
    return new ContactData()
            .withFirstName(fake.name().firstName())
            .withLastName(fake.name().lastName())
            .withMainAddress(fake.address().fullAddress())
            .withHomePhone(fake.phoneNumber().phoneNumber())
            .withMobilePhone(fake.phoneNumber().phoneNumber())
            .withWorkPhone(fake.phoneNumber().phoneNumber())
            .withEmail(fake.internet().emailAddress())
            .withEmail2(fake.internet().emailAddress())
            .withEmail3(fake.internet().emailAddress())
            .withAdPhone(fake.phoneNumber().phoneNumber());
  }

  private Iterator<Object[]> getDataFromCSVFile(String path) throws IOException {
    List<Object[]> list = new ArrayList<>();
    try (var reader = new BufferedReader(new FileReader(
            new File(path)))) {
      var line = reader.readLine();
      while (line != null) {
        String[] split = line.split("; ");
        list.add(new Object[]{new ContactData()
                .withFirstName(split[0]).withMiddleName(split[1]).withLastName(split[2]).withNickname(split[3])
                .withPhoto(new File(split[4])).withJobTitle(split[5]).withCompanyName(split[6]).withMainAddress(split[7])
                .withHomePhone(split[8]).withMobilePhone(split[9]).withWorkPhone(split[10]).withFaxNumber(split[11])
                .withEmail(split[12]).withEmail2(split[13]).withEmail3(split[14]).withWebSite(split[15])
                .withBirthDay(Integer.parseInt(split[16])).withBirthMonth(split[17]).withBirthYear(String.valueOf(split[18]))
                .withAnniversaryDay(String.valueOf(split[19])).withAnniversaryMonth(split[20])
                .withAnniversaryYear(String.valueOf(split[21])).withAdAddress(split[22])
                .withAdPhone(split[23]).withNotes(split[24])
        });
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  public Iterator<Object[]> getDataFromXmlFile(String path) throws IOException {
    try (var reader = new BufferedReader(new FileReader(
            new File(path)))) {
      StringBuilder xml = new StringBuilder();
      var line = reader.readLine();
      while (line != null) {
        xml.append(line);
        line = reader.readLine();
      }
      var xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      //noinspection unchecked
      var contacts = (List<ContactData>) xstream.fromXML(xml.toString());
      return contacts.stream().map((g) -> new Object[]{ g }).collect(Collectors.toList()).iterator();
    }
  }

  public Iterator<Object[]> getDataFromJsonFile(String path) throws IOException {
    try (var reader = new BufferedReader(new FileReader(
            new File(path)))) {
      var json = new StringBuilder();
      String line = reader.readLine();
      while (line != null) {
        json.append(line);
        line = reader.readLine();
      }
      var gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json.toString(), new TypeToken<List<ContactData>>() { }.getType());
      return contacts.stream().map((g) -> new Object[]{ g }).collect(Collectors.toList()).iterator();
    }
  }

  public Iterator<Object[]> getDataFromYamlFile(String path) throws IOException {
    var mapper = new ObjectMapper(new YAMLFactory()); // AutoCloseable
    List<ContactData> contacts = mapper.readValue(
            new File(path), new TypeReference<>() { });
    return contacts.stream().map((g) -> new Object[]{ g }).collect(Collectors.toList()).iterator();
  }
}