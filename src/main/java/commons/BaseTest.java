package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import reportConfig.AllureEnvWriterReport;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    WebDriver driver;
    protected final Logger log;
    protected static String emailAddress, password;


    protected BaseTest() {
        log = LogManager.getLogger(getClass());
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserEnum browser = BrowserEnum.valueOf(browserName.toUpperCase());
        if (browser == BrowserEnum.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser == BrowserEnum.CHROME) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser == BrowserEnum.EDGE_CHROMIUM) {
            System.setProperty("webdriver.edge.driver", GlobalConstants.PROJECT_PATH + File.separator + "browserDriver" + File.separator + "msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Please input the browser name!");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();
        return driver;

    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        boolean isCI = System.getenv("CI") != null;
        BrowserEnum browser = BrowserEnum.valueOf(browserName.trim().toUpperCase());

        switch (browser) {

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addArguments("--disable-notifications");
                ffOptions.addPreference("dom.webnotifications.enabled", false);
                if (isCI) {
                    ffOptions.addArguments("-headless");
                }
                driver = new FirefoxDriver(ffOptions);
                break;

            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-infobars");
                if (isCI) {
                    options.addArguments("--headless=new");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--window-size=1920,1080");
                }
                else {
                    options.addArguments("--start-maximized");
                }

                options.setExperimentalOption("useAutomationExtension", false);
                options.setExperimentalOption("excludeSwitches",
                        Collections.singletonList("enable-automation"));

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enable", false);
                options.setExperimentalOption("prefs", prefs);
                options.setExperimentalOption("useAutomationExtension", false);
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

                driver = new ChromeDriver(options);
                break;

            case EDGE_CHROMIUM:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-infobars");
                edgeOptions.addArguments("--remote-allow-origins=*");
                if (isCI) {
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--window-size=1920,1080");
                } else {
                    edgeOptions.addArguments("--start-maximized");
                }
                Map<String, Object> edgePrefs = new HashMap<>();
                edgePrefs.put("credentials_enable_service", false);
                edgePrefs.put("profile.password_manager_enable", false);
                edgeOptions.setExperimentalOption("prefs", edgePrefs);

                driver = new EdgeDriver(edgeOptions);
                break;

            case COCCOC:
                WebDriverManager.chromedriver().setup();
                ChromeOptions coccocOptions = new ChromeOptions();

                // CocCoc dùng Chromium nên phải set binary
                coccocOptions.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
                coccocOptions.addArguments("--remote-allow-origins=*");
                coccocOptions.addArguments("--disable-notifications");
                coccocOptions.addArguments("--start-maximized");

                driver = new ChromeDriver(coccocOptions);
                break;

            default:
                throw new RuntimeException("Please input a supported browser!");
        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        if (!isCI) {
            driver.manage().window().maximize();
        }

        return driver;
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected void verifyTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
            log.info("✔ VERIFY PASSED: " + message);
        } catch (Throwable e) {
            log.error("❌ VERIFY FAILED: " + message);
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected void closeBrowserDriver() {
        if (driver == null) {
            return;
        }

        String cmd = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            driver.manage().deleteAllCookies();
            driver.quit();

        } catch (Exception e) {

        } finally {
            driver = null;
            if (cmd != null) {
                try {
                    Runtime.getRuntime().exec(cmd).waitFor();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

    //remove
    protected MyAccountPageObject loginUser(String email, String password) {
        HomePageObject homePage = PageGeneratorManager.getPage(HomePageObject.class, driver);
        LoginPageObject loginPage = homePage.openMyAccountHeaderItem("Login", LoginPageObject.class);

        loginPage.inputToEmailTextbox(email);
        loginPage.inputToPasswordTextbox(password);

        return loginPage.clickLoginButton();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        closeBrowserDriver();
    }
}
