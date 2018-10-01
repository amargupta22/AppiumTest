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
import java.util.List;

/**
 * @author sanjitsingh
 */
public class ProductListScreen {

    private AppiumDriver driver;
    private AppiumLibrary appiumLibrary;

    public ProductListScreen(AppiumDriver driver) {
        this.driver = driver;
        this.appiumLibrary = new AppiumLibrary(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }


    @AndroidFindBy(id = "feature_button")
    public MobileElement okGotIt;

    @AndroidFindBy(id = "bar_filter_label")
    public MobileElement filterLabel;

    @AndroidFindBy(id = "btn_filter")
    public MobileElement applyFilter;

    @AndroidFindBy(id = "button_action_positive")
    public MobileElement buttonPositive;

    @AndroidFindBy(id = "button_action_negative")
    public MobileElement buttonNegative;

    @AndroidFindBy(id = "pic_product")
    public MobileElement productList;

    @AndroidFindBy(id = "input_search_bar")
    public MobileElement searchProduct;

    @AndroidFindBy(id = "header_page_search_text_field")
    public MobileElement clickToSearchProduct;

    @AndroidFindBy(id = "text_attribute_1")
    public MobileElement productName;

    @AndroidFindBy(id = "view_product")
    public List<MobileElement> productDetails;

    @AndroidFindBy(id = "text_user")
    public List<MobileElement> productDetailsUserName;


    public MobileElement getOkGotIt() {
        return okGotIt;
    }


    public MobileElement getFilterLabel() {
        return filterLabel;
    }


    public MobileElement getApplyFilter() {
        return applyFilter;
    }


    public MobileElement getButtonPositive() {
        return buttonPositive;
    }


    public MobileElement getButtonNegative() {
        return buttonNegative;
    }


    public MobileElement getProductList() {
        return productList;
    }

    public MobileElement getSearchProduct() {
        return searchProduct;
    }

    public MobileElement getClickToSearchProduct() {
        return clickToSearchProduct;
    }

    public MobileElement getProductName() {
        return productName;
    }


    public List<MobileElement> getProductDetailsUserName() {
        return productDetailsUserName;
    }


    public List<MobileElement> getProductDetails() {
        return productDetails;
    }


}
