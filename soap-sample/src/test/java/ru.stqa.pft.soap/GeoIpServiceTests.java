package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    assertEquals(getCountryAndState("95.55.109.137"), expectedCountryAndState("RU", "66"));
  }

  @Test
  public void testInvalidIp() {
    var location = getCountryAndState("194.28.29.xxx");
    assertNotEquals(location, expectedCountryAndState("RU", "66"));
    // if invalid - always US
    assertEquals(location, expectedCountryAndState("US", "CA"));
  }

  @Test
  public void testBYIp() {
    var ip1 = "185.15.156.22";
    var ip2 = "194.226.120.00";
    var ip3 = "217.23.112.01";
    var expected = expectedCountryAndState("BY", "04");
    assertEquals(getCountryAndState(ip1), expected);
    assertEquals(getCountryAndState(ip2), expected);
    assertEquals(getCountryAndState(ip3), expected);
  }

  @Test
  public void testRUCountry() {
    var countryName1 = "RUSSIAN FEDERATION";
    var countryName2 = "Russian Federation";
    var countryName3 = "russian federation";
    var countryName4 = "rUSSIAN fEDERATION";
    var expected = expectedCountry("RU");
    assertEquals(getCountryISO2(countryName1), expected);
    assertEquals(getCountryISO2(countryName2), expected);
    assertEquals(getCountryISO2(countryName3), expected);
    assertEquals(getCountryISO2(countryName4), expected);
  }

  @Test
  public void testInvalidCountry() {
    var country = getCountryISO2("Russia");
    assertNotEquals(country, expectedCountry("RU"));
    // if invalid - always US
    assertEquals(getCountryISO2(country), expectedCountry("US"));
  }

  @Test
  public void testInvalidISO() {
    assertNotEquals(getCountryName("US"), expectedCountry("United States"));
    // if invalid - always UNITED STATES
    assertEquals(getCountryName("45"), expectedCountry("UNITED STATES"));
  }

  @Test
  public void testISO() {
    assertEquals(getCountryName("RU"), expectedCountry("RUSSIAN FEDERATION"));
    assertEquals(getCountryName("CA"), expectedCountry("CANADA"));
    assertEquals(getCountryName("US"), expectedCountry("UNITED STATES"));
  }

  private GeoIPServiceSoap geoIPServiceSoap12() {
    return new GeoIPService().getGeoIPServiceSoap12();
  }

  private String getCountryAndState(String ip) {
    return geoIPServiceSoap12().getIpLocation(ip);
  }

  private String getCountryISO2(String countryName) {
    return geoIPServiceSoap12().getCountryISO2ByName(countryName);
  }

  private String getCountryName(String countryISO2) {
    return geoIPServiceSoap12().getCountryNameByISO2(countryISO2);
  }

  private String expectedCountryAndState(String country, String state) {
    return String.format("<GeoIP><Country>%s</Country><State>%s</State></GeoIP>", country, state);
  }

  private String expectedCountry(String country) {
    return String.format("<GeoIP><Country>%s</Country></GeoIP>", country);
  }
}
