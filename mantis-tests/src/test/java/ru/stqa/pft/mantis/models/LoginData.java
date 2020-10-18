package ru.stqa.pft.mantis.models;

public class LoginData {
  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public LoginData withUsername(String username) {
    this.username = username;
    return this;
  }

  public LoginData withPassword(String password) {
    this.password = password;
    return this;
  }
}