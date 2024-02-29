package com.selenium.guice;

import com.google.inject.AbstractModule;
import com.selenium.context.Context;
import io.cucumber.guice.ScenarioScoped;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class ProjectModule extends AbstractModule {

    @Override
    public void configure(){
        try{
            bind(WebDriver.class).toProvider(WebDriverProvider.class).in(ScenarioScoped.class);
            bind(Context.class).in(ScenarioScoped.class);
        }
        catch (Exception e){
            log.error(e.getMessage());
            addError(e.getMessage());
        }
    }
}
