package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

import java.util.List;

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
  public By createContactBtnLoc = By.name("submit");
  public By returnToHomePageLinkLoc = By.cssSelector("#content a[href='index.php']");
  public By deleteContactBtnHomePageLoc = By.cssSelector("input[onclick='DeleteSel()']");
  public By updateContactBtnLoc = By.cssSelector("[name=update][value=Update]");
  public By selectAllCheckboxLoc = By.id("MassCB");
  public By deleteContactBtnEditPageLoc = By.cssSelector("[name=update][value=Delete]");
  public By modifyContactBtnLoc = By.name("modifiy");
  public By contactLoc = By.name("entry");
  public By contactInputLoc = By.tagName("input");
  public By cellLoc = By.tagName("td");
  public By contactLoc(int id) {
    return By.cssSelector("input[value='" + id + "']");
  }
  public By editContactBtnLoc(int id) {
    return By.cssSelector("#maintable a[href='edit.php?id=" + id + "']");
  }
  public By viewContactBtnLoc(int id) {
    return By.cssSelector("#maintable a[href='view.php?id=" + id + "']");
  }
  //</editor-fold>

  public ContactHelper(ApplicationManager app) {
    super(app);
  }

  //<editor-fold desc="Methods">
  public void fillForm(ContactData contactData, boolean creation) {
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
    } else {
      Assert.assertFalse(isAnyElementPresent(contactsGroupLoc));
    }
    clearAndType(adAddressLoc, contactData.getAdAddress());
    clearAndType(adPhoneLoc, contactData.getAdPhone());
    clearAndType(notesLoc, contactData.getNotes());
  }

  public void submitCreation() {
    click(createContactBtnLoc);
  }

  public void initDeletionFromHomePage() {
    click(deleteContactBtnHomePageLoc);
  }

  public void submitDeletionFromEditPage() {
    click(deleteContactBtnEditPageLoc);
  }

  public void initModification(int id) {
    getElement(editContactBtnLoc(id)).click();
  }

  public void initModificationFromViewPage() {
    click(modifyContactBtnLoc);
  }

  public void submitModification() {
    click(updateContactBtnLoc);
  }

  public void view(int id) {
    getElement(viewContactBtnLoc(id)).click();
  }

  public void selectById(int id) {
    getElement(contactLoc(id)).click();
  }

  public void selectAll() {
    click(selectAllCheckboxLoc);
  }

  public void returnToHomePage() {
    click(returnToHomePageLinkLoc);
  }

  public void create(ContactData contact) {
    app.goTo().editPage();
    fillForm(contact, true);
    submitCreation();
    verifyMessage("Information entered into address book.");
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    fillForm(contact, false);
    submitModification();
    verifyMessage("Address book updated");
    returnToHomePage();
  }

  public void deleteFromList(ContactData contact) {
    selectById(contact.getId());
    initDeletionFromHomePage();
    closeAlertAndGetItsText();
    verifyMessage("Record successful deleted");
  }

  public void deleteFromEditPage(ContactData contact) {
    initModification(contact.getId());
    submitDeletionFromEditPage();
    verifyMessage("Record successful deleted");
  }

  public void deleteAll() {
    selectAll();
    initDeletionFromHomePage();
    closeAlertAndGetItsText();
    verifyMessage("Record successful deleted");
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    try {
      implicitlyWait(0);
      List<WebElement> elements = getElements(contactLoc);
      for (var element : elements) {
        List<WebElement> cells = element.findElements(cellLoc);
        String lastName = cells.get(1).getText();
        String firstName = cells.get(2).getText();
        String address = cells.get(3).getText();
        int id = Integer.parseInt(element.findElement(contactInputLoc).getAttribute("value"));
        ContactData contact = new ContactData()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withMainAddress(address);
        contacts.add(contact);
      }
      return contacts;
    } finally {
      implicitlyWait(10);
    }
  }
  //</editor-fold>
}