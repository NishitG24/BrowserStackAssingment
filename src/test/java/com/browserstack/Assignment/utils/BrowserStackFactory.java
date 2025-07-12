package com.browserstack.Assignment.utils;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.*;

/**
 * Factory class to create a RemoteWebDriver instance configured for BrowserStack.
 * It supports multiple browsers and devices based on the index provided via system properties.
 */
public class BrowserStackFactory {
// This list defines the BrowserStack options for different browsers and devices.
    private static final List<Map<String, Object>> BSTACK_OPTIONS = List.of(
            bstackOptions("Windows", "11", null, null),                             // Chrome
            bstackOptions("OS X", "Monterey", null, null),                         // Firefox
            bstackOptions("OS X", "Sonoma", null, null),                           // Safari
            bstackOptions(null, null, "Samsung Galaxy S24", "android"),           // Android Mobile
            bstackOptions(null, null, "iPhone 15", "ios")                          // iOS Mobile
    );

    // This method creates a map of options for BrowserStack based on the provided OS, OS version, device, and platform.
    private static Map<String, Object> bstackOptions(String os, String osVersion, String device, String platform) {
        Map<String, Object> options = new HashMap<>();
        options.put("buildName", "ElPais‑Opinion‑Scraper");
        options.put("sessionName", "Thread‑" + UUID.randomUUID());

        if (device != null) {
            options.put("deviceName", device);
            options.put("realMobile", "true");
            options.put("osVersion", osVersion);
        } else {
            options.put("os", os);
            options.put("osVersion", osVersion);
        }

        return options;
    }
// This method creates a RemoteWebDriver instance with the appropriate capabilities for BrowserStack.
    public static RemoteWebDriver create() {
        try {
            String user = System.getenv("BS_USER");
            String key = System.getenv("BS_KEY");

            if (user == null) user = Prop.get("bs.username");
            if (key == null) key = Prop.get("bs.key");

            int index = Integer.parseInt(System.getProperty("bs.index", "0"));
            Map<String, Object> bstackOptions = BSTACK_OPTIONS.get(index);

            MutableCapabilities capabilities = new MutableCapabilities();

            // Standard browserName logic
            if (bstackOptions.containsKey("deviceName")) {
                capabilities.setCapability("browserName", "chrome"); // ignored for mobile, but must be present
            } else if (index == 0) {
                capabilities.setCapability("browserName", "chrome");
            } else if (index == 1) {
                capabilities.setCapability("browserName", "firefox");
            } else if (index == 2) {
                capabilities.setCapability("browserName", "safari");
            }

            // Attach custom options
            capabilities.setCapability("bstack:options", bstackOptions);

            String url = "https://" + user + ":" + key + "@hub.browserstack.com/wd/hub";
            System.out.println("Starting BrowserStack session at: " + url);
            System.out.println("Capabilities: " + capabilities);

            return new RemoteWebDriver(new URL(url), capabilities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot start BrowserStack session", e);
        }
    }
}
