package com.browserstack.Assignment.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
/*
 * This class is the entry point for running Cucumber tests with TestNG.
 * It specifies the location of feature files, step definitions, and hooks.
 * The results will be reported in a pretty format and saved as an HTML report.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.browserstack.Assignment.steps", "com.browserstack.Assignment.hooks"},
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {}

