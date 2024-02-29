package com.selenium.stepdefs;

import com.selenium.guice.WebDriverProvider;
import com.selenium.pages.GooglePage;
import com.selenium.utils.ClickableEvents;
import io.cucumber.java.en.When;

import javax.inject.Inject;

public class GoogleSteps {

    private GooglePage googlePage;

    @Inject
    private WebDriverProvider webDriverProvider;

    @Inject
    private ClickableEvents clickableEvents;

    @Inject
    public GoogleSteps(GooglePage googlePage) {
        this.googlePage = googlePage;
    }

    @When("I search text on Google {string}")
    public void i_search_text_on_google(String string) {
        googlePage.enterText(string);
        googlePage.clickSearchButton();
    }
}
