package com.selenium.hooks;

import com.selenium.utils.env.Env;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import javax.inject.Inject;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Hooks {

    @Inject
    public WebDriver webDriver;
    private static Logger logger = Logger.getLogger(Hooks.class);
    private Env env = new Env();

    @Before
    public void LoginGoogle(){
        String url = Env.env().getString("origin");
        webDriver.get(url);
        logger.info("Before Hook Triggered");
        assertThat ("Browser Title Mismatch", webDriver.getTitle(), equalTo("Google"));
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final  byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            scenario.log("Current URL is " + webDriver.getCurrentUrl());
            logger.error("Scenario failed!");
        }
        logger.info("After Hook Triggered");

        try {
            webDriver.quit();
        }
        catch (Exception e) {
            logger.error("Error closing driver {}" + e.getMessage());
        }
    }
}
