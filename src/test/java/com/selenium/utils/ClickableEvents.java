package com.selenium.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.inject.Inject;
import java.time.Duration;

@Slf4j
public class ClickableEvents {

    private final int LONG_WAIT_TIME = 30;
    private final int MEDIUM_WAIT_TIME = 20;
    private final int SHORT_WAIT_TIME = 10;
    private final int MINI_WAIT_TIME = 3;

    @Inject
    WebDriver webDriver;

    public void waitVisibleClickableClick(WebElement element) {
        try{
            waitVisible(element);
            waitClickable(element);
            click(element);
        }
        catch (Exception e){
            log.error("Click(element) threw exception. Using Actions");
            System.out.println("Click(element) threw exception. Using Actions");
            actionMoveToClick(element);
        }
    }

    private void actionMoveToClick(WebElement element) {
    }

    private void waitClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(SHORT_WAIT_TIME));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(SHORT_WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void sendKeys(WebElement element, CharSequence chars) {
        waitVisible(element);
        element.sendKeys(chars);
    }

    public void click(WebElement element){element.click();}

    public void click(String xpath){getElement(xpath).click();}

    private WebElement getElement(String xpath) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(SHORT_WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return webDriver.findElement(By.xpath(xpath));
    }
}
