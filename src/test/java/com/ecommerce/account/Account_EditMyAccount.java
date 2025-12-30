package com.ecommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import utilitiesConfig.FakerDataHelper;

public class Account_EditMyAccount extends BaseTest {
    WebDriver driver;
    FakerDataHelper fakerData;
    EditAccountPageObject editAccountPage;
    HomePageObject homePage;
    LoginPageObject loginPage;
    MyAccountPageObject myAccountPage;

    String editFirstName, editLastName, editEmailAddress, editPhoneNumber;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void beforeClass(String browserName, String urlValue) {
        driver = getBrowserDriver(browserName, urlValue);
        homePage = PageGeneratorManager.getPage(HomePageObject.class, driver);

        myAccountPage=loginUser("xdutg78sd@gmail.com","123456789");
//        log.info("TC_02_User_Login - Step 01: Login site");
//        loginPage = homePage.openMyAccountHeaderItem("Login", LoginPageObject.class);
//        log.info("TC_02_User_Login - Step 02: Input To Email on Email Login field textbox ");
//        loginPage.inputToEmailTextbox("xdutg78sd@gmail.com");
//
//        log.info("TC_02_User_Login - Step 03: Input To Email on Email Login field textbox ");
//        loginPage.inputToPasswordTextbox("123456789");
//
//        log.info("TC_02_User_Login - Step 04: Click To Login button");
//        myAccountPage = loginPage.clickLoginButton();

        fakerData = FakerDataHelper.getData();

        editFirstName = fakerData.getFirstName();
        editLastName = fakerData.getLastName();
        editEmailAddress = fakerData.getEmail();
        editPhoneNumber = fakerData.getPhone();

    }

    @Test
    public void AccountDashboard_01() {
        log.info("Edit Account - Step 05: Open Edit Account page");
        editAccountPage = myAccountPage.openSidebarPageByName("Edit Account", EditAccountPageObject.class);

        log.info("Edit Account - Step 06: Verify Edit Account page is displayed");
        verifyTrue(editAccountPage.isEditAccountPageDisplayed("My Account Information"));

        log.info("Edit Account - Step 07: Update to First Name textbox :" + editFirstName);
        editAccountPage.inputToFirstNameTextbox(editFirstName);

        log.info("Edit Account - Step 08: Update to Last Name textbox :" + editLastName);
        editAccountPage.inputToLastNameTextbox(editLastName);

        log.info("Edit Account - Step 09: Update to Phone textbox :" + editPhoneNumber);
        editAccountPage.inputToTelephoneTextbox(editPhoneNumber);

        log.info("Edit Account - Step 10: Verify updated data is saved");
        verifyEquals(editAccountPage.getFirstNameValue(), editFirstName);
        verifyEquals(editAccountPage.getLastNameValue(), editLastName);
        verifyEquals(editAccountPage.getTelephoneValue(), editPhoneNumber);

        log.info("Edit Account - Step 11: Click Continue button ");
        myAccountPage = editAccountPage.clickContinueButton();

        log.info("Edit Account - Step 12: Verify success message displayed");
        verifyEquals(editAccountPage.getSuccessMessageText(),"Success: Your account has been successfully updated.");
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
