package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.models.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator extends RandomContact {

  @Parameter(names = "-l", description = "Data language")
  public static String lang;
  @Parameter(names = "-c", description = "Group count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    var generator = new ContactDataGenerator();
    var jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private List<ContactData> generateContacts(int count) throws IOException {
    List<ContactData> contacts = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      contacts.add(getRandomContact());
    }
    return contacts;
  }

  private void run() throws IOException {
    var contacts = generateContacts(count);
    switch (format) {
      case "csv":
        saveAsCsv(contacts, new File(file));
        // -c 5 -f src/test/resources/data/contacts/contacts_en.csv -d csv -l en
        // -c 5 -f src/test/resources/data/contacts/contacts_ru.csv -d csv -l ru
        break;
      case "xml":
        saveAsXml(contacts, new File(file));
        // -c 5 -f src/test/resources/data/contacts/contacts_en.xml -d xml -l en
        // -c 5 -f src/test/resources/data/contacts/contacts_ru.xml -d xml -l ru
        break;
      case "json":
        saveAsJson(contacts, new File(file));
        // -c 5 -f src/test/resources/data/contacts/contacts_en.json -d json -l en
        // -c 5 -f src/test/resources/data/contacts/contacts_ru.json -d json -l ru
        break;
      case "yaml":
        saveAsYaml(contacts, new File(file));
        // -c 5 -f src/test/resources/data/contacts/contacts_en.yaml -d yaml -l en
        // -c 5 -f src/test/resources/data/contacts/contacts_ru.yaml -d yaml -l ru
        break;
      default:
        System.out.println("Unrecognized format: " + format);
        break;
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (var contact : contacts) {
        writer.write(String.format(
                "%s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s\n",
                contact.getFirstName(), contact.getMiddleName(), contact.getLastName(),
                contact.getNickname(), contact.getPhoto(), contact.getJobTitle(),
                contact.getCompanyName(), contact.getMainAddress(), contact.getHomePhone(),
                contact.getMobilePhone(), contact.getWorkPhone(), contact.getFaxNumber(),
                contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getWebSite(),
                contact.getBirthDay(), contact.getBirthMonth(), contact.getBirthYear(),
                contact.getAnniversaryDay(), contact.getAnniversaryMonth(), contact.getAnniversaryYear(),
                contact.getAdAddress(), contact.getAdPhone(), contact.getNotes()));
      }
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    var xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    var xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    var gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    var json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsYaml(List<ContactData> contacts, File file) throws IOException {
    var writer = new ObjectMapper(new YAMLFactory()); // AutoCloseable
    writer.writeValue(file, contacts);
  }
}
