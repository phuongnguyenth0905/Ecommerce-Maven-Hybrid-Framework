package utilitiesConfig;

import commons.GlobalConstants;

import java.io.File;
import java.io.IOException;

public class AutoITHelper {
    public static void uploadFile(String fileAbsolutePath) {
        String scriptPath = GlobalConstants.AUTO_IT_FOLDER + File.separator + "uploadFile.exe";
        try {
            new ProcessBuilder(scriptPath, fileAbsolutePath).start();
        } catch (IOException e) {
            throw new RuntimeException("Cannot run AutoIT upload script", e);
        }
    }

    public static void authenChrome(String username, String password) {
        String scriptPath = GlobalConstants.AUTO_IT_FOLDER + File.separator + "authen_chrome.exe";
        try {
            new ProcessBuilder(scriptPath, username, password).start();
        } catch (IOException e) {
            throw new RuntimeException("Cannot run AutoIT authen chrome script", e);
        }
    }

    public static void authenFirefox(String username, String password) {
        String scriptPath = GlobalConstants.AUTO_IT_FOLDER + File.separator + "authen_firefox.exe";
        try {
            new ProcessBuilder(scriptPath, username, password).start();
        } catch (IOException e) {
            throw new RuntimeException("Cannot run AutoIT authen firefox script", e);
        }
    }
}
