package ru.stqa.pft.addressbook.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

  //<editor-fold desc="Parameters">
  @XStreamOmitField
  @JsonIgnore
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstName = "";

  @Expose
  @Column(name = "lastname")
  private String lastName = "";

  @Expose
  @Column(name = "middlename")
  private String middleName = "";

  @Expose
  @Column(name = "nickname")
  private String nickname = "";

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @Expose
  @Column(name = "company")
  private String companyName = "";

  @Expose
  @Column(name = "title")
  private String jobTitle = "";

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String mainAddress = "";

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone = "";

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone = "";

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone = "";

  @Expose
  @Column(name = "fax")
  @Type(type = "text")
  private String faxNumber = "";

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email = "";

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2 = "";

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3 = "";

  @Expose
  @Column(name = "homepage")
  @Type(type = "text")
  private String webSite = "";

  @Expose
  @Column(name = "bday", columnDefinition = "tinyint")
  private Integer birthDay = 0;

  @Expose
  @Column(name = "bmonth", columnDefinition = "varchar")
  private String birthMonth = "-";

  @Expose
  @Column(name = "byear", columnDefinition = "varchar")
  @Type(type = "text")
  private String birthYear = "";

  @Expose
  @Column(name = "aday", columnDefinition = "tinyint")
  private String anniversaryDay = "0";

  @Expose
  @Column(name = "amonth", columnDefinition = "varchar")
  private String anniversaryMonth = "-";

  @Expose
  @Column(name = "ayear", columnDefinition = "varchar")
  @Type(type = "text")
  private String anniversaryYear = "";

  @Expose
  @Column(name = "address2")
  @Type(type = "text")
  private String adAddress = "";

  @Expose
  @Column(name = "phone2")
  @Type(type = "text")
  private String adPhone = "";

  @Expose
  @Column(name = "notes")
  @Type(type = "text")
  private String notes = "";

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"),
          inverseJoinColumns = @JoinColumn(name = "group_id"))
  private final Set<GroupData> groups = new HashSet<>();

  @XStreamOmitField
  @JsonIgnore
  @Column(name = "im")
  @Type(type = "text")
  private final String im = "";

  @XStreamOmitField
  @JsonIgnore
  @Column(name = "im2")
  @Type(type = "text")
  private final String im2 = "";

  @XStreamOmitField
  @JsonIgnore
  @Column(name = "im3")
  @Type(type = "text")
  private final String im3 = "";

  @XStreamOmitField
  @JsonIgnore
  @Column(name = "deprecated", columnDefinition = "datetime")
  private final String deprecated = "0000-00-00 00:00:00";

  @JsonIgnore
  @Transient
  private String allPhones;

  @JsonIgnore
  @Transient
  private String allEmails;
  //</editor-fold>

  //<editor-fold desc="Getters">
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
    return photo != null ? new File(photo) : null;
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

  public Groups getGroups() {
    return new Groups(groups);
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }
  //</editor-fold>

  //<editor-fold desc="Setters">
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
    this.photo = photo.getPath();
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

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
  //</editor-fold>

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", mainAddress='" + mainAddress + '\'' +
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