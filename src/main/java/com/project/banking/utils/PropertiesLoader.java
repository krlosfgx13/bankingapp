package com.project.banking.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties loadProperties() throws IOException {
        Properties configuration = new Properties();
        InputStream inputStream = PropertiesLoader.class
                .getClassLoader()
                .getResourceAsStream("config/shared/application.properties");
        configuration.load(inputStream);
        inputStream.close();
        return configuration;
    }
}
