package ru.stqa.pft.rest.appmanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {

  public final Properties properties;
  private RestHelper rest;
  private RestAssHelper restAss;

  public ApplicationManager() {
    properties = new Properties();
  }

  public void init() throws IOException {
    loadProperties("target", "local");
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public void loadProperties(String key, String def) throws IOException {
    properties.load(new FileReader(new File(configFile(key, def))));
  }

  public String configFile(String key, String def) {
    return String.format("src/test/resources/config/%s.properties", System.getProperty(key, def));
  }

  public void stop() {
  }

  public RestHelper rest() {
    if (rest == null) {
      rest = new RestHelper(this);
    }
    return rest;
  }

  public RestAssHelper restAss() {
    if (restAss == null) {
      restAss = new RestAssHelper(this);
    }
    return restAss;
  }
}