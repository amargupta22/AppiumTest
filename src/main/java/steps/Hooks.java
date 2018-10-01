package steps;

import com.carousell.library.AppiumLibrary;
import com.carousell.library.AppiumSessionManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.Reporter;

public class Hooks {

    @Before
    public void setup() {
        AppiumSessionManager.getInstance().initiateAppiumDriverInstance();
    }

    @After
    public void teardown(Scenario scenario) {

        long start = System.currentTimeMillis();
        Reporter.log(
                "*********************************************Executing hooks********************************************", true);
        AppiumDriver<MobileElement> driver = AppiumSessionManager.getInstance().driver;
        AppiumLibrary appiumLibrary = new AppiumLibrary(driver);
        if (scenario.isFailed() && driver != null) {

            try {
                String screenshotName = scenario.getName() + "-" + System.currentTimeMillis() + ".jpg";
                scenario.embed(appiumLibrary.createScreenshot(screenshotName), "image/png");
            } catch (Exception e) {
                Reporter.log("Could not capture/embed screenshot\n" + ExceptionUtils.getStackTrace(e),
                        true);
            }
        }

        try {

            if (driver != null) {
                Reporter.log("Found a driver instance", true);
                driver.quit();
            } else {
                Reporter.log("We are not quitting appium session because driver is null");
            }
        } catch (Exception e) {
            Reporter.log("Unable to quit driver session even though it was found to be NOT null\n" + ExceptionUtils.getStackTrace(e), true);

        }


        if (AppiumSessionManager.getInstance().appiumLocalService != null) {
            AppiumSessionManager.getInstance().appiumLocalService.stop();
            if (AppiumSessionManager.getInstance().appiumLocalService.isRunning()) {
                Reporter.log("AppiumServer didn't shut... Trying to quit again....", true);
                AppiumSessionManager.getInstance().appiumLocalService.stop();
            }
        }

        long end = System.currentTimeMillis();
        Reporter.log("****************Time taken to execute hook for device : " + "  --- " + (end - start), true);
        System.gc();


    }
}
