package com.ecommerce.account;

import com.ecommerce.register.TC_01_User_Register;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.ChangePasswordPageObject;
import utilitiesConfig.FakerDataHelper;

public class Account_ChangePassword extends BaseTest {
    WebDriver driver;
    ChangePasswordPageObject changePasswordPage;
    String passwordChange, passwordConfirm;
    FakerDataHelper fakerData;
    MyAccountPageObject myAccountPage;
    HomePageObject homePage;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void beforeClass(String browserName, String urlValue) {
        driver = getBrowserDriver(browserName, urlValue);
        homePage = PageGeneratorManager.getPage(HomePageObject.class, driver);

        myAccountPage=loginUser("xdutg78sd@gmail.com","123456789");

        passwordChange = TC_01_User_Register.password;
        fakerData = FakerDataHelper.getData();
        passwordChange=fakerData.getPassword();
        passwordConfirm=passwordChange;
    }

    @Test
    public void AccountDashboard_01() {
        changePasswordPage=myAccountPage.openSidebarPageByName("Password", ChangePasswordPageObject.class);
        log.info("Edit Account - Step 01: Verify Edit Account page is displayed");
        verifyTrue(changePasswordPage.isChangePasswordPageDisplayed("Change Password"));

        log.info("Edit Account - Step 02: Update to Password textbox :" + passwordChange);
        changePasswordPage.inputToPasswordTextbox("123456789");

        log.info("Edit Account - Step 013: Update to Password Confirm textbox :" + passwordConfirm);
        changePasswordPage.inputToPasswordConfirmTextbox("123456789");

        log.info("Edit Account - Step 06: Click Continue button ");
       myAccountPage = changePasswordPage.clickContinueButton();

        log.info("Edit Account - Step 07: Verify success message displayed");
        verifyEquals(changePasswordPage.getSuccessMessageText(), "Success: Your password has been successfully updated.");
    }
    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
