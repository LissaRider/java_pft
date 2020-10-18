package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.telnet.TelnetClient;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.models.MailMessage;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * По умолчанию в операционной системе Windows Vista/7/8/10 не установлены компоненты "Клиент Telnet" и "Клиент TFTP".
 * Для установки данных служб зайдите в Панель управления > Программы (Программы и компоненты).
 * В разделе "Программы и компоненты" нажмите "Включение или отключение компонентов Windows".
 * В открывшемся окне "Компоненты Windows" отметьте компоненты "Клиент Telnet" и/или "Клиент TFTP".
 * Нажмите кнопку OK и подождите, пока операционная система Windows установит и запустит службу.
 * Совет: Помимо встроенной в ОС Windows службы Telnet, можно воспользоваться любой другой терминальной программой.
 * Например, бесплатной терминальной программой PuTTY (https://putty.org.ru/).
 */
public class JamesHelper {

  private final ApplicationManager app;

  private final TelnetClient telnet;
  private InputStream in;
  private PrintStream out;

  private final Session mailSession;
  private Store store;
  private String mailServer;

  public JamesHelper(ApplicationManager app) {
    this.app = app;
    telnet = new TelnetClient();
    mailSession = Session.getDefaultInstance(System.getProperties());
  }

  public boolean doesUserExist(String name) {
    initTelnetSession();
    write("verify " + name);
    var result = readUntil("exist");
    closeTelnetSession();
    return result != null && result.trim().equals("User " + name + " exist");
  }

  public void createUser(String name, String passwd) {
    initTelnetSession();
    write("adduser " + name + " " + passwd);
    readUntil("User " + name + " added");
    closeTelnetSession();
  }

  public void deleteUser(String name) {
    initTelnetSession();
    write("deluser " + name);
    readUntil("User " + name + " deleted");
    closeTelnetSession();
  }

  private void initTelnetSession() {
    mailServer = app.getProperty("mailserver.host");
    int port = Integer.parseInt(app.getProperty("mailserver.port"));
    var login = app.getProperty("mailserver.adminlogin");
    var password = app.getProperty("mailserver.adminpassword");

    try {
      telnet.connect(mailServer, port);
      System.out.println("\nConnected to: " + mailServer + " " + port);
      in = telnet.getInputStream();
      out = new PrintStream(telnet.getOutputStream());

    } catch (Exception e) {
      e.printStackTrace();
    }
    readUntil("Login id:");
    write(login);
    readUntil("Password:");
    write(password);
    readUntil("Welcome " + login + ". HELP for a list of commands");
  }

  private String readUntil(String pattern) {
    try {
      char lastChar = pattern.charAt(pattern.length() - 1);
      var sb = new StringBuilder();
      char ch = (char) in.read();
      while (true) {
        System.out.print(ch);
        sb.append(ch);
        if (ch == lastChar) {
          if (sb.toString().endsWith(pattern)) {
            return sb.toString();
          }
        }
        ch = (char) in.read();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private void write(String value) {
    try {
      out.println(value);
      out.flush();
      System.out.println("\n" + value);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void closeTelnetSession() {
    write("quit");
  }

  public void drainEmail(String username, String password) throws MessagingException {
    var inbox = openInbox(username, password);
    for (Message message : inbox.getMessages()) {
      message.setFlag(Flags.Flag.DELETED, true);
    }
    closeFolder(inbox);
  }

  private void closeFolder(Folder folder) throws MessagingException {
    folder.close(true);
    store.close();
  }

  private Folder openInbox(String username, String password) throws MessagingException {
    store = mailSession.getStore("pop3");
    store.connect(mailServer, username, password);
    var folder = store.getDefaultFolder().getFolder("INBOX");
    folder.open(Folder.READ_WRITE);
    return folder;
  }

  public List<MailMessage> waitForMail(String username, String password, long timeout) throws MessagingException {
    long now = System.currentTimeMillis();
    while (System.currentTimeMillis() < now + timeout) {
      var allMail = getAllMail(username, password);
      if (allMail.size() > 0) {
        return allMail;
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No mail :(");
  }

  public List<MailMessage> getAllMail(String username, String password) throws MessagingException {
    var inbox = openInbox(username, password);
    var messages = Arrays
            .stream(inbox.getMessages())
            .map(JamesHelper::toModelMail)
            .collect(Collectors.toList());
    closeFolder(inbox);
    return messages;
  }

  public static MailMessage toModelMail(Message m) {
    try {
      return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
    } catch (MessagingException | IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    var mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().orElseThrow();
    var regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}