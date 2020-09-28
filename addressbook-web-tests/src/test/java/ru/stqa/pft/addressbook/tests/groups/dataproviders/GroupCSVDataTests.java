package ru.stqa.pft.addressbook.tests.groups.dataproviders;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCSVDataTests extends TestBase {

  @Test(testName = "Проверка создания группы (CSV)",
          dataProvider = "groupsFromCsvFile", dataProviderClass = GroupDataProviders.class)
  public void paramTestCsvGroupCreation(GroupData group) {
    app.goTo().groupsPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded(group.withId(after
            .stream()
            .mapToInt(GroupData::getId)
            .max()
            .orElseThrow())))); /*java 11: getAsInt() to orElseThrow()*/
  }
}
