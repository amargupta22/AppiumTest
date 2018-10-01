/**
 *
 */
package com.carousell.library;

import com.carousell.constants.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import java.io.File;
import java.net.ServerSocket;

/**
 * @author sanjitsingh
 */
public class AppiumSessionManager {

    private static AppiumSessionManager instance = null;
    public AppiumDriver driver = null;
    public AppiumDriverLocalService appiumLocalService = null;


    public static AppiumSessionManager getInstance() {
        if (instance == null) {
            instance = new AppiumSessionManager();
        }
        return instance;
    }


    public void initiateAppiumDriverInstance() {
        int chromeDriverPort = getPort();
        int port = getPort();
        int bootstrapPort = getPort();
        DesiredCapabilities serverCapabilities = new DesiredCapabilities();
        serverCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60 * 15);
        serverCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        // os specific details :
        DesiredCapabilities clientCapabilities = new DesiredCapabilities();
        serverCapabilities.setCapability(MobileCapabilityType.APP, Constants.DEFAULT_APP_PATH);
        clientCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, Constants.PACKAGE_NAME);
        clientCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        clientCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, Constants.START_ACTIVITY_NAME);
        clientCapabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 90000 * 10);


        // build appium instance
        // create folder to contain appium logs
        String systemOs = System.getProperty("os.name").toLowerCase();
        String appiumLogsFilePathPrefix = "";
        if (systemOs.startsWith("mac")) {
            appiumLogsFilePathPrefix = "/Users/";
        } else {
            appiumLogsFilePathPrefix = "/home/";
        }
        String appiumLogsPathName = appiumLogsFilePathPrefix + System.getProperty("user.name") + "/Desktop/appiumLogs/";
        File appiumLogsPath = new File(appiumLogsPathName);
        if (!appiumLogsPath.exists()) {
            appiumLogsPath.mkdir();
        }
        Reporter.log("The server capibilties passed to appium is" + serverCapabilities.toString(), true);
        AppiumServiceBuilder appiumBuilder = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")).withCapabilities(serverCapabilities)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "warn").withLogFile(new File(appiumLogsPathName + ".txt"))
                .withArgument(AndroidServerFlag.SUPPRESS_ADB_KILL_SERVER).withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, Integer.toString(bootstrapPort))
                .withArgument(AndroidServerFlag.CHROME_DRIVER_PORT, Integer.toString(chromeDriverPort)).withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(AndroidServerFlag.CHROME_DRIVER_EXECUTABLE, System.getProperty("user.dir") + "/res/chromedriver").usingPort(port);
        Reporter.log("appium service builder build completed", true);
        appiumLocalService = appiumBuilder.build();
        Reporter.log("appium service builder build is completed", true);
        Reporter.log("Appium local service" + appiumLocalService.toString(), true);
        appiumLocalService.start();
        Reporter.log("appium service builder start is completed", true);
        Reporter.log("The appium service get url" + appiumLocalService.getUrl(), true);

        driver = new AndroidDriver<>(appiumLocalService.getUrl(), clientCapabilities);
    }

    private int getPort() {
        int port = 0;
        try (ServerSocket socket = new ServerSocket(0)) {
            socket.setReuseAddress(true);
            port = socket.getLocalPort();
        } catch (Exception e) {
            Reporter.log(ExceptionUtils.getStackTrace(e), true);
        }
        return port;
    }


}
