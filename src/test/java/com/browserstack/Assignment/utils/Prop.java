package com.browserstack.Assignment.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* * Utility class to load properties from a file named "browserstack.properties".
 * This file should contain key-value pairs for BrowserStack credentials and configurations.
 */
public class Prop {

    private static final Properties props = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("browserstack.properties");
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Could not load browserstack.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
