package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"};
    System.out.println("String[] langs = {\"Java\", \"C#\", \"Python\", \"PHP\"};");
//    for (int i = 0; i < langs.length; i++) {
//      System.out.println("Я хочу выучить " + langs[i]);
    for (String l : langs) {
      System.out.println("Массив: Я хочу выучить " + l);
    }

    System.out.println("\nList<String> languages = new ArrayList<>();");
    List<String> languages = new ArrayList<>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
    languages.add("PHP");
//    for (String l : languages) {
//      System.out.println("Я хочу выучить " + l);
//    }
    for (int i = 0; i < languages.size(); i++)
      System.out.println("Я хочу выучить " + languages.get(i));

    List<String> langsList = Arrays.asList("Java", "C#", "Python", "PHP");
    System.out.println("\nList<String> languages = Arrays.asList(\"Java\", \"C#\", \"Python\", \"PHP\");");
//    for (String l : langsList) {
//      System.out.println("Я хочу выучить " + l);
//    }
    for (int i = 0; i < langsList.size(); i++)
      System.out.println("Я хочу выучить " + langsList.get(i));
  }
}

