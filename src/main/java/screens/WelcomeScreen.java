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
public class WelcomeScreen {

    private AppiumDriver driver;
    private AppiumLibrary appiumLibrary;

    public WelcomeScreen(AppiumDriver driver) {
        this.driver = driver;
        this.appiumLibrary = new AppiumLibrary(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }


    @AndroidFindBy(id = "btn_close")
    public MobileElement closeButton;

    @AndroidFindBy(id = "button_close")
    public MobileElement successfullListedClose;

    @AndroidFindBy(id = "text_tab")
    public MobileElement tab;


    public MobileElement getCloseButton() {
        return closeButton;
    }


    public MobileElement getSuccessfullListedClose() {
        return successfullListedClose;
    }


    public MobileElement getTab() {
        return tab;
    }


}
