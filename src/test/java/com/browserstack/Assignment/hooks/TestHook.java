package com.browserstack.Assignment.hooks;

import com.browserstack.Assignment.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
/*
**
 * This class contains hooks that run before and after each scenario.
 * It initializes and quits the WebDriver instance.
 */
public class TestHook {

    @Before
    public void setUp() {
        DriverFactory.initDriver();
}

@After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

}
