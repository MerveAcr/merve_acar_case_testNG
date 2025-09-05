package com.insider.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for reading configuration properties from test.properties file
 */
public class ConfigReader {
    private static Properties properties;
    
    static {
        try {
            properties = new Properties();
            FileInputStream input = new FileInputStream("src/main/resources/test.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Get a property value by key
     * @param key - Property key
     * @return String - Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Get the location filter value from properties
     * @return String - Location filter value
     */
    public static String getLocationFilter() {
        return getProperty("filter.location");
    }
    
    /**
     * Get the department filter value from properties
     * @return String - Department filter value
     */
    public static String getDepartmentFilter() {
        return getProperty("filter.department");
    }
}
