package ru.stqa.pft.addressbook.models;

public class LoginData {
  private final String username;
  private final String password;

  public LoginData(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}