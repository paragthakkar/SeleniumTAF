package com.selenium.utils;

import java.util.Properties;

public class WebActions {
    public static String getProperty(String key){
        ConfigReader configReader = new ConfigReader();
        Properties properties = configReader.initProp();
        return properties.getProperty(key);
    }
}
