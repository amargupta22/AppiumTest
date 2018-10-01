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
import org.junit.Assert;
import org.testng.Reporter;
import screens.ProductListScreen;
import screens.WelcomeScreen;

/**
 * @author sanjitsingh
 */
public class ProductListingSteps {

    AppiumDriver driver = AppiumSessionManager.getInstance().driver;
    AppiumLibrary appiumLibrary = new AppiumLibrary(driver);
    WelcomeScreen welcomeScreen = new WelcomeScreen(driver);
    ProductListScreen productListScreen = new ProductListScreen(driver);
    AdbUtility adbUtility = new AdbUtility();


    @When("^User verifies the item listed under recent in Everything Else category$")
    public void verifyListing() {
        checkForPopUps();
        appiumLibrary.clickOnMobileElement(welcomeScreen.getTab());
        if (appiumLibrary.isElementPresentOnScreen(appiumLibrary.getElementByText("See All"))) {
            appiumLibrary.clickOnMobileElement(appiumLibrary.getElementByText("See All"));
        } else {
            adbUtility.forceStopApp(Constants.PACKAGE_NAME);
            appiumLibrary.launchTheApp();
            appiumLibrary.clickOnMobileElement(appiumLibrary.getElementByText("See All"));
        }
        appiumLibrary.click_searchelement_text("Everything Else", 0);
        if (appiumLibrary.isElementPresentOnScreen(productListScreen.getOkGotIt()))
            appiumLibrary.clickOnMobileElement(productListScreen.getOkGotIt());
        Reporter.log("Verifying Listing", true);
        appiumLibrary.clickOnMobileElement(productListScreen.getFilterLabel());
        appiumLibrary.clickOnMobileElement(appiumLibrary.getElementByText("Recent"));
        appiumLibrary.clickOnMobileElement(productListScreen.getApplyFilter());
        appiumLibrary.waitForMobileElement(productListScreen.getProductList(), Constants.DEFAULT_OBJECT_WAIT_TIME);
        appiumLibrary.clickOnMobileElement(productListScreen.getClickToSearchProduct());
        appiumLibrary.enterText(productListScreen.getSearchProduct(), AddingProductSteps.name);
        appiumLibrary.pressSearch();
        appiumLibrary.waitForMobileElement(productListScreen.getProductList(), Constants.DEFAULT_OBJECT_WAIT_TIME);
        Assert.assertTrue(appiumLibrary.isElementPresentOnScreen(productListScreen.getProductName()));
        String productName = appiumLibrary.fetchText(productListScreen.getProductName());
        String listingName = AddingProductSteps.name;
        Assert.assertEquals("Product Name Mismatch", productName, listingName);
    }

    private void checkForPopUps() {
        Reporter.log("Checking for pop-ups", true);
        if (!appiumLibrary.isElementPresentOnScreen(appiumLibrary.getElementByText("Successfully listed!"))) {
            appiumLibrary.pressBack();
        }

        if (appiumLibrary.isElementPresentOnScreen(appiumLibrary.getElementByText("Successfully listed!"))) {
            appiumLibrary.clickOnMobileElement(welcomeScreen.getSuccessfullListedClose());

        }

    }


}