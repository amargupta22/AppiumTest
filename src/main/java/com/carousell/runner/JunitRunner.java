/**
 *
 */
package com.carousell.runner;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * @author sanjitsingh
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = "steps", features = "src/main/resources/features/")
public class JunitRunner {

    @AfterClass
    public static void setup() {
        Reporter.loadXMLConfig(new File("src/main/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Mac OSX");
        Reporter.setTestRunnerOutput("Sample test runner output message");
    }

}
