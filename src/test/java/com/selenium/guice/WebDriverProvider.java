package com.selenium.guice;

import com.google.inject.Provider;
import com.selenium.utils.WebActions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;

public class WebDriverProvider implements Provider<WebDriver> {

    private static final String BROWSER_CONF = WebActions.getProperty("browser");
    public static final String HEADLESS_CONF = WebActions.getProperty("headless");
    WebDriver webDriver;

    @Override
    public WebDriver get() {
        try{
            return initBrowserManager();
        }
        catch (MalformedURLException e){
            throw new RuntimeException(e);
        }
    }

    private WebDriver initBrowserManager() throws MalformedURLException{
        String browser = System.getProperty(BROWSER_CONF);

        if(browser == null){
            return initChrome();
        }

        if(browser.equals("firefox")){
            return initFirefox();
        }
        else if (browser.equals("edge")) {
            return initEdge();

        }
        else{
            return initChrome();
        }
    }

    private WebDriver initChrome() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(Arrays.asList(
                "--no-sandbox",
                "--verbose",
                "--disable-dev-shm-usage",
                "--window-size=1920,1080",
                "--ignore-certificate-errors",
                "--force-device-scale-factor=1",
                "--disable-notifications",
                "--remote-allow-origins=*"
        ));
        if(HEADLESS_CONF.equals("true")){
            chromeOptions.addArguments("--headless");
        }
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(chromeOptions);
        return webDriver;
    }

    private WebDriver initFirefox() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver(firefoxOptions);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return webDriver;
    }

    private WebDriver initEdge() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments(Arrays.asList(
                "--disable-gpu",
                "--disable-software-rasterizer",
                "--force-device-scale-factor=1",
                "--high-dpi-support=0.75",
                "--no-sandbox",
                "--ignore-certificate-errors"
        ));
        WebDriverManager.edgedriver().setup();
        webDriver = new EdgeDriver(edgeOptions);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return webDriver;
    }
}
