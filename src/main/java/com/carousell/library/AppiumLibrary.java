/**
 *
 */
package com.carousell.library;


import com.carousell.constants.Locators;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.InvalidCoordinatesException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author sanjitsingh
 */
public class AppiumLibrary {

    private AppiumDriver driver;

    public AppiumLibrary(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    // To click on any element
    // putting log in try catch to prevent exception
    public void clickOnMobileElement(MobileElement element) {

        try {
            waitForMobileElement(element, 10);
            Reporter.log("Clicking on element " + element, true);
            if (element != null) {
                element.click();
            } else {
                Assert.fail("Element is null i.e. element was not captured by PageFactory initiation");
            }
        } catch (Exception e) {
            Assert.fail("Unable to click on element" + "\n" + ExceptionUtils.getStackTrace(e));
        }
    }

    // To enter some text in any element
    public void enterText(MobileElement element, String value) {
        Reporter.log("Entering text- " + value, true);
        if (element == null) {
            Assert.fail(String.valueOf(element) + " is null, i.e. element was not captured by PageFactory initialization");
            return;
        }
        clickOnMobileElement(element);
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            Reporter.log(" Unable to enter text : " + value + " in element.", true);
        }

    }

    public String fetchText(MobileElement element) {
        String text = null;
        Reporter.log("Fetching text from element- " + element, true);
        if (element == null) {
            Assert.fail(String.valueOf(element) + " is null, i.e. element was not captured by PageFactory initialization");
            return null;
        }
        try {
            text = element.getText();
        } catch (Exception e) {
            Reporter.log(" Unable to get text of element ", true);
        }
        return text;
    }

    public boolean isElementPresentOnScreen(MobileElement element) {
        boolean isElementPresent = false;
        try {
            element.isDisplayed();
            isElementPresent = true;
        } catch (Exception e) {
            isElementPresent = false;
        }
        return isElementPresent;
    }


    public void closes_keyboard() {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            Reporter.log("Keyboard not visible", true);
        }

    }


    /**
     * @param value text of the element
     * @return MobileElement
     */
    public MobileElement getElementByText(String value) {

        MobileElement element = null;
        try {
            By elementBy = MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + value + "\")");
            element = (MobileElement) driver.findElement(elementBy);
        } catch (Exception e) {
            Reporter.log("Unable to find element by text :  " + value, true);
        }
        return element;

    }


    public boolean waitForMobileElement(MobileElement element, int timeout) {
        Reporter.log("Waiting for element: " + String.valueOf(element), true);
        boolean found = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
            found = true;
        } catch (TimeoutException timeoutException) {
            Reporter.log(String.valueOf(element) + "Not found", true);
        }
        return found;
    }

    public void pressBack() {
        try {
            ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);

        } catch (Exception e) {
            Reporter.log("Could not press back on " + e.getMessage(), true);
        }
    }

    public void pressSearch() {
        try {
            ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.ENTER);

        } catch (Exception e) {
            Reporter.log("Could not press Search on Keyboard " + e.getMessage(), true);
        }
    }


    public void click_searchelement_text(String value, int index) {
        MobileElement element = searchElementInList(Locators.TEXT, value, index);
        clickOnMobileElement(element);

    }

    public MobileElement searchElementInList(Locators locator, String value, int index) {
        Reporter.log("Searching element in list: " + value + " index :" + index, true);
        MobileElement object = null;
        object = searchElementInListAndroid(locator, value, index);
        return object;
    }

    public MobileElement searchElementInListAndroid(Locators locator, String value, int index) {
        Reporter.log("Searching element in Android: " + value + " index :" + index, true);
        By by = null;
        MobileElement object = null;
        try {
            switch (locator) {
                case TEXT:
                    by = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(1000).scrollIntoView(new UiSelector().text(\"" + value + "\"));");
                    break;

                case ACCESSIBILITY_ID:
                    by = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(1000).scrollIntoView(new UiSelector().description(\"" + value + "\"));");
                    break;

                case CLASSNAME:
                    by = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(1000).scrollIntoView(new UiSelector().className(\"" + value + "\").index("
                            + index + "));");
                    break;

                case RESOURCE_ID:
                    by = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(1000).scrollIntoView(new UiSelector().resourceId(\"" + value + "\"));");
                    break;

                default:
                    Reporter.log(locator + " is not a valid android locator to search ", true);
            }
            object = (MobileElement) driver.findElement(by);
        } catch (Exception e) {
            Reporter.log("Object not found in list", true);
        }
        return object;
    }


    public void scrollDirection(int num, String direction) {
        Dimension dimensions = driver.manage().window().getSize();
        TouchAction action = new TouchAction(driver);
        Double screenHeightStart = dimensions.getHeight() * 0.8;
        int scrollStart = screenHeightStart.intValue();
        Double screenHeightEnd = dimensions.getHeight() * 0.2;
        int scrollEnd = screenHeightEnd.intValue();
        Double screeWidhthStart = dimensions.getWidth() * 0.8;
        int swipeStart = screeWidhthStart.intValue();
        Double screenWidhthEnd = dimensions.getWidth() * 0.02;
        int swipeEnd = screenWidhthEnd.intValue();

        try {
            for (int i = 0; i < num; i++) {
                switch (direction) {
                    case "UP":
                        action.longPress(0, scrollStart).moveTo(0, scrollEnd).release().perform();
                        break;

                    case "DOWN":
                        action.longPress(0, scrollEnd).moveTo(0, scrollStart).release().perform();
                        break;

                    case "LEFT":
                        action.longPress(swipeStart, 0).moveTo(swipeEnd, 0).release().perform();
                        break;

                    case "RIGHT":
                        action.longPress(swipeEnd, 0).moveTo(swipeStart, 0).release().perform();
                        break;

                    default:
                        break;
                }
                Thread.sleep(2000);
            }

        } catch (Exception e) {
            Reporter.log("Scroll not working\n" + ExceptionUtils.getStackTrace(e), true);
        }

    }

    /**
     * Method to take screenshot and store in Screenshot directory of workspace.
     *
     * @param screenshotName Pass null to take screenshot only in case of scenario failure, otherwise name of the file (acceptable extensions: .jpg, .jpeg, .png)
     * @return byte array of the screenshot captured
     */
    public byte[] createScreenshot(String screenshotName) {
        try {
            if (screenshotName != null && !screenshotName.endsWith(".jpg") && !screenshotName.endsWith(".png") && !screenshotName.endsWith(".jpeg")) {
                screenshotName += ".jpeg";
            }
            File file = driver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("Screenshot/" + screenshotName));
            Reporter.log(
                    "<a class=\"test-popup-link\" href=\"Screenshot/" + screenshotName + "\">"
                            + "Screenshot here" + "</a>", true);
            FileInputStream fis = new FileInputStream(new File("Screenshot/" + screenshotName));
            byte[] screenshotBytes = IOUtils.toByteArray(fis);
            return screenshotBytes;
        } catch (Exception e) {
            Reporter.log("Not able to store screenshot", true);
        }
        return null;
    }

    public void launchTheApp() {
        Reporter.log("Launch the app", true);
        try {
            driver.launchApp();
        } catch (Exception e) {
            Reporter.log(ExceptionUtils.getStackTrace(e), true);
        }
    }


}
