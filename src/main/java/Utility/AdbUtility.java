/**
 *
 */
package Utility;

import com.shell.utils.ShellCommandExecutor;
import org.testng.Reporter;

import java.util.ArrayList;

/**
 * @author sanjitsingh
 */
public class AdbUtility {


    public String adbExecute(String command) {
        ArrayList<String> devicesCapturedFromCommandLine = new ArrayList<String>();
        Reporter.log("command ..." + command, true);
        String adbDevicesLogs = getAdbevices();
        String[] adbDeviceLoglines = adbDevicesLogs.split("\n");
        for (int i = 0; i < adbDeviceLoglines.length; i++) {
            if (!adbDeviceLoglines[i].startsWith("List of devices") && adbDeviceLoglines[i].contains("device")) {
                devicesCapturedFromCommandLine.add(adbDeviceLoglines[i].split("device")[0].trim());
            }
        }
        return ShellCommandExecutor.executeCommands("adb -s " + devicesCapturedFromCommandLine.get(0) + " " + command);

    }


    public String getAdbevices() {
        String adbDevices = ShellCommandExecutor.executeCommands("adb devices");
        return adbDevices;
    }

    public void forceStopApp(String packageName) {
        adbExecute("shell am force-stop " + packageName);
    }


}
