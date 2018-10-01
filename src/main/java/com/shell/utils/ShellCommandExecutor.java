/**
 *
 */
package com.shell.utils;

import org.testng.Reporter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author sanjitsingh
 */
public class ShellCommandExecutor {

    public static String executeCommands(String command) {
        Reporter.log(command, true);
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                output.append(line + "\n");
            }

            while ((line = error.readLine()) != null) {
                output.append("Error Ocurred : \n" + line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

}
