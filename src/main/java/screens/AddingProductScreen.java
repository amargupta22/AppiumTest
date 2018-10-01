/**
 *
 */
package screens;

import com.carousell.library.AppiumLibrary;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


/**
 * @author sanjitsingh
 */
public class AddingProductScreen {

    private AppiumDriver driver;
    private AppiumLibrary appiumLibrary;

    public AddingProductScreen(AppiumDriver driver) {
        this.driver = driver;
        this.appiumLibrary = new AppiumLibrary(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }


    @AndroidFindBy(id = "button_sell")
    public MobileElement sellButton;

    @AndroidFindBy(id = "pic_thumbnail")
    public MobileElement existingPicture;

    @AndroidFindBy(id = "fab_capture")
    public MobileElement clickPicture;

    @AndroidFindBy(id = "action_done")
    public MobileElement nextButton;

    @AndroidFindBy(id = "rv_image_picker")
    public MobileElement imagePicker;

    @AndroidFindBy(className = "android.widget.EditText")
    public MobileElement listingTitle;

    @AndroidFindBy(id = "text_input")
    public MobileElement price;

    @AndroidFindBy(id = "label_button_submit")
    public MobileElement submitButton;


    public MobileElement getSellButton() {
        return sellButton;
    }


    public MobileElement getExistingPicture() {
        return existingPicture;
    }


    public MobileElement getClickPicture() {
        return clickPicture;
    }


    public MobileElement getNextButton() {
        return nextButton;
    }


    public MobileElement getImagePicker() {
        return imagePicker;
    }


    public MobileElement getListingTitle() {
        return listingTitle;
    }


    public MobileElement getPrice() {
        return price;
    }


    public MobileElement getSubmitButton() {
        return submitButton;
    }


}
