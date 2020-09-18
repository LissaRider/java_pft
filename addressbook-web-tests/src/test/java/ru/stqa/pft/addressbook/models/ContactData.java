package ru.stqa.pft.addressbook.models;

import java.util.Objects;

public class ContactData {
  private String firstName;
  private String lastName;
  private String middleName;
  private String nickname;
  private String fileSource;
  private String jobTitle;
  private String companyName;
  private String mainAddress;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String faxNumber;
  private String email;
  private String email2;
  private String email3;
  private String webSite;
  private Integer birthDay;
  private String birthMonth;
  private String birthYear;
  private String anniversaryDay;
  private String anniversaryMonth;
  private String anniversaryYear;
  private String adAddress;
  private String adPhone;
  private String notes;
  private int id;

  public ContactData(String firstName, String middleName, String lastName, String nickname,
                     String fileSource, String jobTitle, String companyName, String mainAddress,
                     String homePhone, String mobilePhone, String workPhone, String faxNumber,
                     String email, String email2, String email3, String webSite,
                     Integer birthDay, String birthMonth, String birthYear,
                     String anniversaryDay, String anniversaryMonth, String anniversaryYear,
                     String adAddress, String adPhone, String notes) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.fileSource = fileSource;
    this.jobTitle = jobTitle;
    this.companyName = companyName;
    this.mainAddress = mainAddress;
    this.homePhone = homePhone;
    this.mobilePhone = mobilePhone;
    this.workPhone = workPhone;
    this.faxNumber = faxNumber;
    this.email = email;
    this.email2 = email2;
    this.email3 = email3;
    this.webSite = webSite;
    this.birthDay = birthDay;
    this.birthMonth = birthMonth;
    this.birthYear = birthYear;
    this.anniversaryDay = anniversaryDay;
    this.anniversaryMonth = anniversaryMonth;
    this.anniversaryYear = anniversaryYear;
    this.adAddress = adAddress;
    this.adPhone = adPhone;
    this.notes = notes;
  }

  public ContactData(int id, String firstName, String middleName, String lastName, String nickname,
                     String fileSource, String jobTitle, String companyName, String mainAddress,
                     String homePhone, String mobilePhone, String workPhone, String faxNumber,
                     String email, String email2, String email3, String webSite,
                     Integer birthDay, String birthMonth, String birthYear,
                     String anniversaryDay, String anniversaryMonth, String anniversaryYear,
                     String adAddress, String adPhone, String notes) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.fileSource = fileSource;
    this.jobTitle = jobTitle;
    this.companyName = companyName;
    this.mainAddress = mainAddress;
    this.homePhone = homePhone;
    this.mobilePhone = mobilePhone;
    this.workPhone = workPhone;
    this.faxNumber = faxNumber;
    this.email = email;
    this.email2 = email2;
    this.email3 = email3;
    this.webSite = webSite;
    this.birthDay = birthDay;
    this.birthMonth = birthMonth;
    this.birthYear = birthYear;
    this.anniversaryDay = anniversaryDay;
    this.anniversaryMonth = anniversaryMonth;
    this.anniversaryYear = anniversaryYear;
    this.adAddress = adAddress;
    this.adPhone = adPhone;
    this.notes = notes;
  }

  public ContactData(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public ContactData(int id, String firstName, String lastName, String mainAddress) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.mainAddress = mainAddress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(mainAddress, that.mainAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, mainAddress);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", mainAddress='" + mainAddress + '\'' +
            ", id=" + id +
            '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickname() {
    return nickname;
  }

  public String getFileSource() {
    return fileSource;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getMainAddress() {
    return mainAddress;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getFaxNumber() {
    return faxNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getWebSite() {
    return webSite;
  }

  public Integer getBirthDay() {
    return birthDay;
  }

  public String getBirthMonth() {
    return birthMonth;
  }

  public String getBirthYear() {
    return birthYear;
  }

  public String getAnniversaryDay() {
    return anniversaryDay;
  }

  public String getAnniversaryMonth() {
    return anniversaryMonth;
  }

  public String getAnniversaryYear() {
    return anniversaryYear;
  }

  public String getAdAddress() {
    return adAddress;
  }

  public String getAdPhone() {
    return adPhone;
  }

  public String getNotes() {
    return notes;
  }
}