package ru.stqa.pft.addressbook.bdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:bdd",
        plugin = {"pretty", "html:build/reports/cucumber-report"})
public class GroupTests extends AbstractTestNGCucumberTests {
}