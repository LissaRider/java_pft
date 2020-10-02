package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection() {
    try {
      String dBname = "addressbook";
      String user = "root";
      String password = "";
      String dBUrl = "jdbc:mysql://localhost/" + dBname + "?user=" + user + "&password=" + password;
      Connection connection = DriverManager.getConnection(dBUrl);
      Statement statement = connection.createStatement();
      String sqlRequest = "select group_id,group_name,group_header,group_footer from group_list";
      ResultSet result = statement.executeQuery(sqlRequest);
      Groups groups = new Groups();
      while (result.next()) {
        groups.add(new GroupData()
                .withId(result.getInt("group_id"))
                .withName(result.getString("group_name"))
                .withHeader(result.getString("group_header"))
                .withFooter(result.getString("group_footer")));
      }
      result.close();
      statement.close();
      connection.close();
      System.out.println(groups);
    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}