package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

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

  private Contacts contactCache = null;

  //<editor-fold desc="Methods">
  public void fillForm(ContactData contact, boolean creation) {
    clearAndType(firstNameLoc, contact.getFirstName());
    clearAndType(middleNameLoc, contact.getMiddleName());
    clearAndType(lastNameLoc, contact.getLastName());
    clearAndType(nicknameLoc, contact.getNickname());
    uploadFile(inputFileLoc,contact.getPhoto());
    clearAndType(jobTitleLoc, contact.getJobTitle());
    clearAndType(companyNameLoc, contact.getCompanyName());
    clearAndType(mainAddressLoc, contact.getMainAddress());
    clearAndType(homePhoneLoc, contact.getHomePhone());
    clearAndType(mobilePhoneLoc, contact.getMobilePhone());
    clearAndType(workPhoneLoc, contact.getWorkPhone());
    clearAndType(faxNumberLoc, contact.getFaxNumber());
    clearAndType(emailLoc, contact.getEmail());
    clearAndType(email2Loc, contact.getEmail2());
    clearAndType(email3Loc, contact.getEmail3());
    clearAndType(webSiteLoc, contact.getWebSite());
    selectByIndex(birthDayLoc, contact.getBirthDay());
    selectByText(birthMonthLoc, contact.getBirthMonth());
    clearAndType(birthYearLoc, contact.getBirthYear());
    selectByValue(anniversaryDayLoc, contact.getAnniversaryDay());
    selectByValue(anniversaryMonthLoc, contact.getAnniversaryMonth());
    clearAndType(anniversaryYearLoc, contact.getAnniversaryYear());
    Groups groups = contact.getGroups();
    if (creation && groups.size() > 0) {
      Assert.assertTrue(groups.size() == 1);
      getGroup(contact);
    } else {
      Assert.assertFalse(isAnyElementPresent(contactsGroupLoc));
    }
    clearAndType(adAddressLoc, contact.getAdAddress());
    clearAndType(adPhoneLoc, contact.getAdPhone());
    clearAndType(notesLoc, contact.getNotes());
  }

  public void getGroup(ContactData contact) {
    var group = contact.getGroups().iterator().next();
    selectByText(contactsGroupLoc, group.getName());
  }

  public void submitCreation() {
    click(createContactBtnLoc);
    waitForPageLoad(); // IE
  }

  public void initDeletionFromHomePage() {
    click(deleteContactBtnHomePageLoc);
  }

  public void submitDeletionFromEditPage() {
    click(deleteContactBtnEditPageLoc);
    waitForPageLoad(); // IE
  }

  public void initModification(int id) {
    getElement(editContactBtnLoc(id)).click();
  }

  public void initModificationFromViewPage() {
    click(modifyContactBtnLoc);
  }

  public void submitModification() {
    click(updateContactBtnLoc);
    waitForPageLoad(); // IE
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
    waitForPageLoad(); // IE
  }

  public int count() {
    try {
      implicitlyWait(0);
      return getElements(contactLoc).size();
    } finally {
      implicitlyWait(10);
    }
  }

  public void create(ContactData contact) {
    app.goTo().editPage();
    fillForm(contact, true);
    submitCreation();
    contactCache = null;
    verifyMessage("Information entered into address book.");
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    fillForm(contact, false);
    submitModification();
    contactCache = null;
    verifyMessage("Address book updated");
    returnToHomePage();
  }

  public void deleteFromList(ContactData contact) {
    selectById(contact.getId());
    initDeletionFromHomePage();
    closeAlertAndGetItsText();
    contactCache = null;
    verifyMessage("Record successful deleted");
  }

  public void deleteFromEditPage(ContactData contact) {
    initModification(contact.getId());
    submitDeletionFromEditPage();
    contactCache = null;
    verifyMessage("Record successful deleted");
  }

  public void deleteAll() {
    selectAll();
    initDeletionFromHomePage();
    closeAlertAndGetItsText();
    contactCache = null;
    verifyMessage("Record successful deleted");
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initModification(contact.getId());
    String firstname = getElement(firstNameLoc).getAttribute("value");
    String lastname = getElement(lastNameLoc).getAttribute("value");
    String address = getElement(mainAddressLoc).getAttribute("value");
    String home = getElement(homePhoneLoc).getAttribute("value");
    String mobile = getElement(mobilePhoneLoc).getAttribute("value");
    String work = getElement(workPhoneLoc).getAttribute("value");
    String add = getElement(adPhoneLoc).getAttribute("value");
    String email1 = getElement(emailLoc).getAttribute("value");
    String email2 = getElement(email2Loc).getAttribute("value");
    String email3 = getElement(email3Loc).getAttribute("value");
    driver.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withFirstName(firstname)
            .withLastName(lastname)
            .withMainAddress(address)
            .withHomePhone(home)
            .withMobilePhone(mobile)
            .withWorkPhone(work)
            .withAdPhone(add)
            .withEmail(email1)
            .withEmail2(email2)
            .withEmail3(email3);
  }

  public Contacts all() {
    if (contactCache != null)
      return new Contacts(contactCache);
    contactCache = new Contacts();
    try {
      implicitlyWait(0);
      List<WebElement> elements = getElements(contactLoc);
      for (var element : elements) {
        List<WebElement> cells = element.findElements(cellLoc);
        int id = Integer.parseInt(element.findElement(contactInputLoc).getAttribute("value"));
        String lastName = cells.get(1).getText();
        String firstName = cells.get(2).getText();
        String address = cells.get(3).getText();
        String allEmails = cells.get(4).getText();
        String allPhones = cells.get(5).getText();
        ContactData contact = new ContactData()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withMainAddress(address)
                .withAllEmails(allEmails)
                .withAllPhones(allPhones);
        contactCache.add(contact);
      }
      return new Contacts(contactCache);
    } finally {
      implicitlyWait(10);
    }
  }
  //</editor-fold>
}