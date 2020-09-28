package ru.stqa.pft.addressbook.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {

  @XStreamOmitField
  @JsonIgnore
  private int id = Integer.MAX_VALUE;
  @Expose
  private String firstName;
  @Expose
  private String lastName;
  @Expose
  private String middleName;
  @Expose
  private String nickname;
  private File photo;
  @Expose
  private String jobTitle;
  @Expose
  private String companyName;
  @Expose
  private String mainAddress;
  @Expose
  private String homePhone;
  @Expose
  private String mobilePhone;
  @Expose
  private String workPhone;
  @Expose
  private String faxNumber;
  @Expose
  private String email;
  @Expose
  private String email2;
  @Expose
  private String email3;
  @Expose
  private String webSite;
  @Expose
  private Integer birthDay;
  @Expose
  private String birthMonth;
  @Expose
  private String birthYear;
  @Expose
  private String anniversaryDay;
  @Expose
  private String anniversaryMonth;
  @Expose
  private String anniversaryYear;
  @Expose
  private String adAddress;
  @Expose
  private String adPhone;
  @Expose
  private String notes;
  @JsonIgnore
  private String allPhones;
  @JsonIgnore
  private String allEmails;

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public int getId() {
    return id;
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

  public File getPhoto() {
    return photo;
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

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ContactData withJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
    return this;
  }

  public ContactData withCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public ContactData withMainAddress(String mainAddress) {
    this.mainAddress = mainAddress;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withFaxNumber(String faxNumber) {
    this.faxNumber = faxNumber;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withWebSite(String webSite) {
    this.webSite = webSite;
    return this;
  }

  public ContactData withBirthDay(Integer birthDay) {
    this.birthDay = birthDay;
    return this;
  }

  public ContactData withBirthMonth(String birthMonth) {
    this.birthMonth = birthMonth;
    return this;
  }

  public ContactData withBirthYear(String birthYear) {
    this.birthYear = birthYear;
    return this;
  }

  public ContactData withAnniversaryDay(String anniversaryDay) {
    this.anniversaryDay = anniversaryDay;
    return this;
  }

  public ContactData withAnniversaryMonth(String anniversaryMonth) {
    this.anniversaryMonth = anniversaryMonth;
    return this;
  }

  public ContactData withAnniversaryYear(String anniversaryYear) {
    this.anniversaryYear = anniversaryYear;
    return this;
  }

  public ContactData withAdAddress(String adAddress) {
    this.adAddress = adAddress;
    return this;
  }

  public ContactData withAdPhone(String adPhone) {
    this.adPhone = adPhone;
    return this;
  }

  public ContactData withNotes(String notes) {
    this.notes = notes;
    return this;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(mainAddress, that.mainAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, mainAddress);
  }
}