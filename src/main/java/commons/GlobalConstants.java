package commons;
import java.io.File;
import java.nio.file.Paths;
public class GlobalConstants {
    // ============ PROJECT PATH ============
    public static final String PROJECT_PATH=System.getProperty("user.dir");
    // ===== DATAFILES ROOT (external folder) =====
    public static final String DATAFILES_PATH =
            Paths.get(PROJECT_PATH, "datafiles").toString();
    // ============ FILE PATHS ============
    public static final String UPLOAD_FILE_FOLDER = Paths.get(DATAFILES_PATH, "uploadFiles").toString();

    public static final String DOWNLOAD_FILE_FOLDER = Paths.get(DATAFILES_PATH, "downloadFiles").toString();

    public static final String BROWSER_LOG_FOLDER = Paths.get(DATAFILES_PATH, "browserLogs").toString();

    public static final String EVIDENCE_FOLDER = Paths.get(DATAFILES_PATH, "evidence").toString();

    public static final String TEMP_FOLDER =  Paths.get(DATAFILES_PATH, "temp").toString();

    public static final String TEST_DATA_FOLDER = Paths.get(DATAFILES_PATH, "testData").toString();

    public static final String API_DATA_FOLDER =  Paths.get(DATAFILES_PATH, "api").toString();

    public static final String DB_DATA_FOLDER = Paths.get(DATAFILES_PATH, "database").toString();
    public static final String AUTO_IT_FOLDER =
            new File(GlobalConstants.class.getClassLoader().getResource("autoIT").getPath()).getAbsolutePath();

    public static final String REPORT_FOLDER = PROJECT_PATH + "/reports/";
    public static final String SCREENSHOT_FOLDER = REPORT_FOLDER + "screenshots/";
    // ============ URL ============
    public static final String DEV_URL = "https://demo.nopcommerce.com/";
    public static final String TEST_URL = "https://test.nopcommerce.com/";
    public static final String STAGING_URL = "https://staging.nopcommerce.com/";
   // ============ TIMEOUT ============
    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;
    public static final String DB_URL="jdbc:mysql://localhost:1234";
    public static final String DB_NAME="testauto";
    public static final String DB_USER="root";
    public static final String DB_PASS="admin";
    public static final String JAVA_VERSION=System.getProperty("java.version");
    // ============ OS ============
    public static final String OS_NAME=System.getProperty("os.name");

    //public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + getDirectorySlash("browserLog");

    public static String getDirectorySlash(String folderName) {
        if (isMac() || isUnix() || isSolaris()) {
            folderName = "/" + folderName + "/";
        } else {
            folderName = "\\" + folderName + "\\";
        }
        return folderName;
    }

    public static boolean isWindows() {

        return (GlobalConstants.OS_NAME.toLowerCase().indexOf("win") >= 0);
    }

    public static boolean isMac() {

        return (GlobalConstants.OS_NAME.toLowerCase().indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (GlobalConstants.OS_NAME.toLowerCase().indexOf("nix") >= 0 || GlobalConstants.OS_NAME.toLowerCase().indexOf("nux") >= 0 || GlobalConstants.OS_NAME.toLowerCase().indexOf("aix") >= 0);
    }

    public static boolean isSolaris() {
        return (GlobalConstants.OS_NAME.toLowerCase().indexOf("sunos") >= 0);
    }
}

