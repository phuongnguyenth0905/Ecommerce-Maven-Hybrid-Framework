package com.ecommerce.login;

import com.ecommerce.register.TC_01_User_Register;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;

public class TC_02_User_Login extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    LoginPageObject loginPage;
    MyAccountPageObject myAccountPage;
    String emailLogin, passwordLogin;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void beforeClass(String browserName, String urlValue) {
        emailLogin = emailAddress;
        passwordLogin = password;

        log.info("TC_02_User_Login - Step 01: Open Home page");
        driver = getBrowserDriver(browserName, urlValue);
        homePage = PageGeneratorManager.getPage(HomePageObject.class, driver);
    }

    @Test
    public void TC_01_Login() {
         log.info("TC_02_User_Login - Step 03: Click Login link");
        loginPage = homePage.openMyAccountHeaderItem("Login", LoginPageObject.class);

        log.info("TC_02_User_Login - Step 04: Input To Email on Email Login field textbox is " + emailLogin);
        loginPage.inputToEmailTextbox(emailLogin);

        log.info("TC_02_User_Login - Step 05: Input To Email on Email Login field textbox is " + passwordLogin);
        loginPage.inputToPasswordTextbox(passwordLogin);

        log.info("TC_02_User_Login - Step 06: Click To Login button");
        myAccountPage = loginPage.clickLoginButton();

    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
