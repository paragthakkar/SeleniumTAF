package com.selenium.hooks;

import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class Hooks {

    @Inject
    public WebDriver webDriver;

    @Before
    public void LoginGoogle(){
        webDriver.get("https://www.google.com");
        log.info("Before Hook Triggered");
        assertThat ("Browser Title Mismatch", webDriver.getTitle(), equalTo("Google"));
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final  byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            scenario.log("Current URL is " + webDriver.getCurrentUrl());
            log.error("Scenario failed!");
        }
        log.info("After Hook Triggered");

        try {
            webDriver.quit();
        }
        catch (Exception e) {
            log.error("Error closing driver {}" + e.getMessage());
        }
    }
}
