package com.selenium.acceptencetests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.selenium"},
        features = "src/test/resources/features/",
        plugin = {
                "pretty",
                "junit:target/cucumber-reports/Cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags = "@test",
        monochrome = true
)
public class Runner {}
