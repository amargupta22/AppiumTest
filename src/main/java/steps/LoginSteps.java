/**
 *
 */
package steps;

import Utility.AdbUtility;
import com.carousell.constants.Constants;
import com.carousell.library.AppiumLibrary;
import com.carousell.library.AppiumSessionManager;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.testng.Reporter;
import screens.LoginScreen;

/**
 * @author sanjitsingh
 */
public class LoginSteps {

    AppiumDriver driver = AppiumSessionManager.getInstance().driver;
    AppiumLibrary appiumLibrary = new AppiumLibrary(driver);
    LoginScreen loginScreen = new LoginScreen(driver);

    @When("^User logs in$")
    public void login() {
        allowPermissions();
        Reporter.log("Login", true);
        appiumLibrary.clickOnMobileElement(loginScreen.getLoginButton());
        if (appiumLibrary.isElementPresentOnScreen(loginScreen.getPicker())) {
            appiumLibrary.clickOnMobileElement(loginScreen.getNoneOfTheAbove());
        }
        appiumLibrary.enterText(loginScreen.getUsername(), Constants.USERNAME);
        appiumLibrary.enterText(loginScreen.getPassword(), Constants.PASSWORD);
        appiumLibrary.clickOnMobileElement(loginScreen.getLoginButton());

    }


    private void allowPermissions() {
        AdbUtility adb = new AdbUtility();
        adb.adbExecute("shell pm grant " + Constants.PACKAGE_NAME + " android.permission.CAMERA");
        adb.adbExecute("shell pm grant " + Constants.PACKAGE_NAME + " android.permission.RECORD_AUDIO");
        adb.adbExecute("shell pm grant " + Constants.PACKAGE_NAME + " android.permission.RECEIVE_SMS");
        adb.adbExecute("shell pm grant " + Constants.PACKAGE_NAME + " android.permission.ACCESS_FINE_LOCATION");
        adb.adbExecute("shell pm grant " + Constants.PACKAGE_NAME + " android.permission.WRITE_EXTERNAL_STORAGE");
        adb.adbExecute("shell pm grant " + Constants.PACKAGE_NAME + " android.permission.READ_EXTERNAL_STORAGE");
        adb.adbExecute("shell pm grant " + Constants.PACKAGE_NAME + " android.permission.ACCESS_COARSE_LOCATION");
    }


}