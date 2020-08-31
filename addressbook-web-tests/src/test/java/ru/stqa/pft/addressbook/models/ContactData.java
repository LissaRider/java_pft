package ru.stqa.pft.addressbook.models;

public class ContactData {
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nickname;
  private final String source;
  private final String jobTitle;
  private final String companyName;
  private final String mainAddress;
  private final String homePhone;
  private final String mobilePhone;
  private final String workPhone;
  private final String faxNumber;
  private final String email;
  private final String email2;
  private final String email3;
  private final String webSite;
  private final int birthDay;
  private final String birthMonth;
  private final String birthYear;
  private final String anniversaryDay;
  private final String anniversaryMonth;
  private final String anniversaryYear;
  private final String adAddress;
  private final String adPhone;
  private final String notes;

  public ContactData(String firstName, String middleName, String lastName, String nickname,
                     String filePath, String jobTitle, String companyName, String mainAddress,
                     String homePhone, String mobilePhone, String workPhone, String faxNumber,
                     String email, String email2, String email3, String webSite,
                     int birthDay, String birthMonth, String birthYear,
                     String anniversaryDay, String anniversaryMonth, String anniversaryYear,
                     String adAddress, String adPhone, String notes) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.source = filePath;
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

  public String getSource() {
    return source;
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

  public int getBirthDay() {
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
