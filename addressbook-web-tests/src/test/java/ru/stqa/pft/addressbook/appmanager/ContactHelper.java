package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;

public class ContactHelper extends HelperBase {

  //<editor-fold desc="Locators">
  public By contactsTableLoc = By.id("maintable");
  public By firstNameLoc = By.name("firstname");
  public By middleNameLoc = By.name("middlename");
  public By lastNameLoc = By.name("lastname");
  public By nicknameLoc = By.name("nickname");
  public By inputFileLoc = By.name("photo");
  public By jobTitleLoc = By.name("title");
  public By companyNameLoc = By.name("company");
  public By mainAddressLoc = By.name("address");
  public By homePhoneLoc = By.name("home");
  public By mobilePhoneLoc = By.name("mobile");
  public By workPhoneLoc = By.name("work");
  public By faxNumberLoc = By.name("fax");
  public By emailLoc = By.name("email");
  public By email2Loc = By.name("email2");
  public By email3Loc = By.name("email3");
  public By webSiteLoc = By.name("homepage");
  public By birthDayLoc = By.name("bday");
  public By birthMonthLoc = By.name("bmonth");
  public By birthYearLoc = By.name("byear");
  public By anniversaryDayLoc = By.name("aday");
  public By anniversaryMonthLoc = By.name("amonth");
  public By anniversaryYearLoc = By.name("ayear");
  public By contactsGroupLoc = By.name("new_group");
  public By adAddressLoc = By.name("address2");
  public By adPhoneLoc = By.name("phone2");
  public By notesLoc = By.name("notes");
//  public By topCreateContactBtnLoc = By.xpath(".//input[@name='submit'][1]");
//  public By bottomCreateContactBtnLoc = By.xpath(".//input[@name='submit'][last()]");
  public By createContactBtnLoc = By.name("submit");
  public By returnToHomePageLinkLoc = By.xpath(".//*[@id='content']//a[@href='index.php']");
  public By returnToEditContactPageLinkLoc = By.xpath(".//*[@id='content']//a[@href='edit.php']");
  public By contactCheckboxLoc = By.xpath(".//*[@id='maintable']//*[@name='selected[]']");
  public By deleteContactBtnHomePageLoc = By.xpath(".//input[@onclick='DeleteSel()']");
  public By editContactBtnLoc = By.xpath(".//*[@id='maintable']//a[starts-with(@href,'edit.php')]");
  public By viewContactBtnLoc = By.xpath(".//*[@id='maintable']//a[starts-with(@href,'view.php')]");
  public By updateContactBtnLoc = By.xpath(".//*[@name='update' and @value='Update']");
  public By selectAllCheckboxLoc = By.id("MassCB");
  public By deleteContactBtnEditPageLoc = By.xpath(".//*[@name='update' and @value='Delete']");
  public By modifyContactBtnLoc = By.name("modifiy");
  //</editor-fold>

  public ContactHelper(ApplicationManager app) {
    super(app);
  }

  //<editor-fold desc="Methods">
  public void fillContactForm(ContactData contactData, boolean creation) {
    clearAndType(firstNameLoc, contactData.getFirstName());
    clearAndType(middleNameLoc, contactData.getMiddleName());
    clearAndType(lastNameLoc, contactData.getLastName());
    clearAndType(nicknameLoc, contactData.getNickname());
    uploadFile(inputFileLoc, contactData.getFileSource());
    clearAndType(jobTitleLoc, contactData.getJobTitle());
    clearAndType(companyNameLoc, contactData.getCompanyName());
    clearAndType(mainAddressLoc, contactData.getMainAddress());
    clearAndType(homePhoneLoc, contactData.getHomePhone());
    clearAndType(mobilePhoneLoc, contactData.getMobilePhone());
    clearAndType(workPhoneLoc, contactData.getWorkPhone());
    clearAndType(faxNumberLoc, contactData.getFaxNumber());
    clearAndType(emailLoc, contactData.getEmail());
    clearAndType(email2Loc, contactData.getEmail2());
    clearAndType(email3Loc, contactData.getEmail3());
    clearAndType(webSiteLoc, contactData.getWebSite());
    selectByIndex(birthDayLoc, contactData.getBirthDay());
    selectByText(birthMonthLoc, contactData.getBirthMonth());
    clearAndType(birthYearLoc, contactData.getBirthYear());
    selectByValue(anniversaryDayLoc, contactData.getAnniversaryDay());
    selectByValue(anniversaryMonthLoc, contactData.getAnniversaryMonth());
    clearAndType(anniversaryYearLoc, contactData.getAnniversaryYear());
    if (creation) {
      selectByValue(contactsGroupLoc, "[none]");
//      selectByText(contactsGroupLoc, "[none]");
//      selectByIndex(contactsGroupLoc, 0);}
    } else {
      Assert.assertFalse(isAnyElementPresent(contactsGroupLoc));
    }
    clearAndType(adAddressLoc, contactData.getAdAddress());
    clearAndType(adPhoneLoc, contactData.getAdPhone());
    clearAndType(notesLoc, contactData.getNotes());
  }

  public void submitContactCreation() {
//    click(topCreateContactBtnLoc); /*только верхняя кнопка*/
//    click(bottomCreateContactBtnLoc); /*только нижняя кнопка*/
//    click(createContactBtnLoc); /*первая кнопка из найденных*/
    click(createContactBtnLoc); /*любая кнопка из найденных*/
  }

  public void returnToEditContactPage() {
    click(returnToEditContactPageLinkLoc);
  }

  public void returnToHomePage() {
    click(returnToHomePageLinkLoc);
  }

  public void selectAnyContact() {
    click(contactCheckboxLoc);
  }

  public void initContactDeletionOnHomePage() {
    click(deleteContactBtnHomePageLoc);
  }

  public void initAnyContactModification() {
    click(editContactBtnLoc);
  }

  public void viewAnyContact() {
    click(viewContactBtnLoc);
  }

  public void submitContactModification() {
    click(updateContactBtnLoc);
  }

  public void selectAllContacts() {
    click(selectAllCheckboxLoc);
  }

  public void initContactDeletionOnEditContactPage() {
    click(deleteContactBtnEditPageLoc);
  }

  public void initContactModification() {
    click(modifyContactBtnLoc);
  }

  public void createContact(ContactData contact) {
    app.nav().goToEditContactPage();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void modifyContact(ContactData contact) {
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public void deleteAnyContactFromList() {
    selectAnyContact();
    initContactDeletionOnHomePage();
    closeAlertAndGetItsText();
  }

  public void deleteAnyContactOnEditPage() {
    initAnyContactModification();
    initContactDeletionOnEditContactPage();
  }

  public void deleteAllContacts() {
    selectAllContacts();
    initContactDeletionOnHomePage();
    closeAlertAndGetItsText();
  }

  public void createContacts(ContactData contact, int n) {
    app.nav().goToEditContactPage();
    for (int i = 1; i <= n; i++) {
      fillContactForm(contact, true);
      submitContactCreation();
      returnToEditContactPage();
    }
    app.nav().goToHomePage();
  }

  public boolean isAnyContactPresent() {
    return isAnyElementPresent(contactCheckboxLoc);
  }

  public void verifyContactPresence(ContactData newContact) {
    app.nav().goToHomePage();
    if (!isAnyContactPresent()) createContact(newContact);
  }
  //</editor-fold>
}