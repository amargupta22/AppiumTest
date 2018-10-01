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
public class LoginScreen {

    private AppiumDriver driver;
    private AppiumLibrary appiumLibrary;

    public LoginScreen(AppiumDriver driver) {
        this.driver = driver;
        this.appiumLibrary = new AppiumLibrary(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }


    @AndroidFindBy(id = "login_button")
    public MobileElement loginButton;

    @AndroidFindBy(id = "com.google.android.gms:id/credential_picker_layout")
    public MobileElement picker;

    @AndroidFindBy(id = "com.google.android.gms:id/credential_avatar")
    public MobileElement avatar;

    @AndroidFindBy(id = "com.google.android.gms:id/cancel")
    public MobileElement noneOfTheAbove;

    @AndroidFindBy(id = "layout_username")
    public MobileElement username;

    @AndroidFindBy(id = "layout_password")
    public MobileElement password;


    public MobileElement getLoginButton() {
        return loginButton;
    }

    public MobileElement getPicker() {
        return picker;
    }


    public MobileElement getAvatar() {
        return avatar;
    }


    public MobileElement getNoneOfTheAbove() {
        return noneOfTheAbove;
    }


    public MobileElement getUsername() {
        return username;
    }


    public MobileElement getPassword() {
        return password;
    }


}
