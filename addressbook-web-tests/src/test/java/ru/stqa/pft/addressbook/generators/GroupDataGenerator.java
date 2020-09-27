package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.models.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    var generator = new GroupDataGenerator();
    var jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData()
              .withName(String.format("group %s", i))
              .withHeader(String.format("header %s", i))
              .withFooter(String.format("footer %s", i)));
    }
    return groups;
  }

  private void run() throws IOException {
    var groups = generateGroups(count);
    if (format.equals("csv")) {
      saveAsCsv(groups, new File(file)); /* -c 10 -f src/test/resources/data/groups/groups.csv -d csv */
    } else if (format.equals("xml")) {
      saveAsXml(groups, new File(file)); /* -c 10 -f src/test/resources/data/groups/groups.xml -d xml */
    } else if (format.equals("json")) {
      saveAsJson(groups, new File(file)); /* -c 10 -f src/test/resources/data/groups/groups.json -d json */
    } else if (format.equals("yaml")) {
      saveAsYaml(groups, new File(file)); /* -c 10 -f src/test/resources/data/groups/groups.yaml -d yaml */
    } else {
      System.out.println("Unrecognized format: " + format);
    }
  }

  private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (var group : groups) {
        writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
      }
    }
  }

  private void saveAsXml(List<GroupData> groups, File file) throws IOException {
    var xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    var xml = xstream.toXML(groups);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsJson(List<GroupData> groups, File file) throws IOException {
    var gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    var json = gson.toJson(groups);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsYaml(List<GroupData> groups, File file) throws IOException {
    var writer = new ObjectMapper(new YAMLFactory()); // AutoCloseable
    writer.writeValue(file, groups);
  }
}