/**
 *
 */
package steps;

import com.carousell.constants.Constants;
import com.carousell.library.AppiumLibrary;
import com.carousell.library.AppiumSessionManager;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.testng.Reporter;
import screens.AddingProductScreen;

import java.util.Date;
import java.util.Random;

/**
 * @author sanjitsingh
 */
public class AddingProductSteps {

    AppiumDriver driver = AppiumSessionManager.getInstance().driver;
    AppiumLibrary appiumLibrary = new AppiumLibrary(driver);
    AddingProductScreen addingProductScreen = new AddingProductScreen(driver);
    static String name;

    @When("^User lists an item under Everything Else category$")
    public void list_an_item() {
        Date date = new Date();
        long timeMilli = date.getTime();
        Reporter.log("Listing", true);
        appiumLibrary.waitForMobileElement(addingProductScreen.getSellButton(), Constants.DEFAULT_OBJECT_WAIT_TIME);
        appiumLibrary.clickOnMobileElement(addingProductScreen.getSellButton());
        appiumLibrary.clickOnMobileElement(appiumLibrary.getElementByText("NEW PHOTO"));
        appiumLibrary.clickOnMobileElement(addingProductScreen.getClickPicture());
        appiumLibrary.clickOnMobileElement(addingProductScreen.getExistingPicture());
        appiumLibrary.clickOnMobileElement(addingProductScreen.getNextButton());
        appiumLibrary.waitForMobileElement(addingProductScreen.getImagePicker(), Constants.DEFAULT_OBJECT_WAIT_TIME);
        appiumLibrary.click_searchelement_text("Everything Else", 0);
        name = Constants.PRODUCT_LISTING_NAME + timeMilli;
        appiumLibrary.enterText(addingProductScreen.getListingTitle(), name);
        appiumLibrary.enterText(addingProductScreen.getPrice(), random());
        appiumLibrary.closes_keyboard();
        appiumLibrary.clickOnMobileElement(addingProductScreen.getSubmitButton());
    }


    private String random() {
        int max = 100;
        int min = 1;
        Random rand = new Random();
        int random = rand.nextInt((max - min) + 1) + min;
        return String.valueOf(random);
    }

}