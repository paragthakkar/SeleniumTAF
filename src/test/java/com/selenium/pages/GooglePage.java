package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.selenium.utils.ClickableEvents;

import javax.inject.Inject;

public class GooglePage {

    private ClickableEvents clickableEvents;

    @Inject
    public GooglePage(WebDriver webDriver, ClickableEvents clickableEvents){
        PageFactory.initElements(webDriver, this);
        this.clickableEvents = clickableEvents;
    }

    @FindBy(name = "q")
    public WebElement searchBar;

    @FindBy(name = "btnK")
    public WebElement searchButton;

    public void enterText(String text){
        clickableEvents.waitVisibleClickableClick(searchBar);
        clickableEvents.sendKeys(searchBar, text);
    }

    public void clickSearchButton(){
        searchButton.click();
    }
}
