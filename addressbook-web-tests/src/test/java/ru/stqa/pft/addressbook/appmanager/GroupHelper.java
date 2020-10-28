package ru.stqa.pft.addressbook.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

  //<editor-fold desc="Locators">
  public By addGroupBtnLoc = By.name("new");
  public By groupNameLoc = By.name("group_name");
  public By groupHeaderLoc = By.name("group_header");
  public By groupFooterLoc = By.name("group_footer");
  public By createGroupBtnLoc = By.name("submit");
  public By returnToGroupsPageLinkLoc = By.cssSelector("#content a[href='group.php']");
  public By deleteGroupBtnLoc = By.name("delete");
  public By editGroupBtnLoc = By.name("edit");
  public By updateGroupBtnLoc = By.name("update");
  public By groupLoc = By.className("group");
  public By groupInputLoc = By.tagName("input");
  public By groupCheckboxLoc = By.name("selected[]");
  public By groupLoc(int id) {
    return By.cssSelector("input[value='" + id + "']");
  }
  //</editor-fold>

  public GroupHelper(ApplicationManager app) {
    super(app);
  }

  private Groups groupCache = null;

  //<editor-fold desc="Methods">
  @Step("Я получаю идентификатор для группы")
  public int id() {
    var groups = app.db().groups();
    int id;
    if (!groups.isEmpty())
      id = groups.stream()
              .mapToInt(GroupData::getId)
              .max()
              .orElseThrow() + 1;
    else id = 1;
    return id;
  }

  @Step("Я открываю страницу создания группы")
  public void initCreation() {
    click(addGroupBtnLoc);
  }

  @Step("Я заполняю поля группы данными")
  public void fillForm(GroupData groupData) {
    clearAndType(groupNameLoc, groupData.getName());
    clearAndType(groupHeaderLoc, groupData.getHeader());
    clearAndType(groupFooterLoc, groupData.getFooter());
  }

  @Step("Я подтверждаю создание группы")
  public void submitCreation() {
    click(createGroupBtnLoc);
    waitForPageLoad(); // IE
  }

  @Step("Я выбираю группу по идентификатору")
  public void selectById(int id) {
    getElement(groupLoc(id)).click();
  }

  @Step("Я подтверждаю удаление группы")
  public void submitDeletion() {
    click(deleteGroupBtnLoc);
    waitForPageLoad(); // IE
  }

  @Step("Я возвращаюсь на страницу со списком групп")
  public void returnToGroupsPage() {
    click(returnToGroupsPageLinkLoc);
    waitForPageLoad(); // IE
  }

  @Step("Я открываю страницу редактирования группы")
  public void initModification() {
    click(editGroupBtnLoc);
  }

  @Step("Я подтверждаю все изменения группы")
  public void submitModification() {
    click(updateGroupBtnLoc);
    waitForPageLoad(); // IE
  }

  @Step("Я выбираю все группы")
  private void selectAll() {
    var checkboxes = getElements(groupCheckboxLoc);
    for (var checkbox : checkboxes) {
      if (!checkbox.isSelected())
        checkbox.click();
    }
  }

  @Step("Я получаю актуальное количество групп")
  public int count() {
    try {
      implicitlyWait(0);
      return getElements(groupLoc).size();
    } finally {
      implicitlyWait(10);
    }
  }

  @Step("Я создаю группу")
  public void create(GroupData group) {
    initCreation();
    fillForm(group);
    submitCreation();
    groupCache = null;
    verifyMessage("A new group has been entered into the address book.");
    returnToGroupsPage();
  }

  @Step("Я редактирую группу")
  public void modify(GroupData group) {
    selectById(group.getId());
    initModification();
    fillForm(group);
    submitModification();
    groupCache = null;
    verifyMessage("Group record has been updated.");
    returnToGroupsPage();
  }

  @Step("Я удаляю группу")
  public void delete(GroupData group) {
    selectById(group.getId());
    submitDeletion();
    groupCache = null;
    verifyMessage("Group has been removed.");
    returnToGroupsPage();
  }

  @Step("Я удаляю все группы")
  public void deleteAll() {
    selectAll();
    submitDeletion();
    groupCache = null;
    verifyMessage("Group has been removed.");
    returnToGroupsPage();
  }

  @Step("Я получаю актуальный список групп")
  public Groups all() {
    if (groupCache != null)
      return new Groups(groupCache);
    groupCache = new Groups();
    try {
      implicitlyWait(0);
      List<WebElement> elements = getElements(groupLoc);
      for (WebElement element : elements) {
        String name = element.getText();
        int id = Integer.parseInt(element.findElement(groupInputLoc).getAttribute("value"));
        GroupData group = new GroupData().withId(id).withName(name);
        groupCache.add(group);
      }
      return new Groups(groupCache);
    } finally {
      implicitlyWait(10);
    }
  }
  //</editor-fold>
}