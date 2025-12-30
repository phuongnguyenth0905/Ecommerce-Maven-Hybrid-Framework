package com.ecommerce.register;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.SuccessPageObject;
import utilitiesConfig.FakerDataHelper;

public class TC_01_User_Register extends BaseTest {
    WebDriver driver;
    FakerDataHelper fakerData;
    RegisterPageObject registerPage;
    HomePageObject homePage;
    MyAccountPageObject myAccountPage;
    SuccessPageObject successPage;

    public static String firstname, lastName,  phoneNumber,  confirmPassword;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void beforeClass(String browserName, String urlValue) {
        if(driver==null) {
            driver = getBrowserDriver(browserName, urlValue);
        }
        log.info("TC_01_User_Register - Step 01: Open Home page");
        homePage = PageGeneratorManager.getPage(HomePageObject.class, driver);

        fakerData = FakerDataHelper.getData();
        firstname = fakerData.getFirstName();
        lastName = fakerData.getLastName();
        emailAddress = fakerData.getEmail();
        phoneNumber = fakerData.getPhone();
        password = fakerData.getPassword();

    }

    @Test
    public void TC_01_Register_New_User() {
        log.info("TC_01_User_Register - Step 02: Open Register Page");
        registerPage=homePage.openMyAccountHeaderItem("Register", RegisterPageObject.class);
        log.info("TC_01_User_Register - Step 03: Input Register Data");

        log.info("TC_01_User_Register - Step 04: Input data First Name textbox is: " + firstname);
        registerPage.inputFirstNameTextbox(firstname);

        log.info("TC_01_User_Register - Step 05: Input data Last Name textbox is " + lastName);
        registerPage.inputLastNameTextbox(lastName);

        log.info("TC_01_User_Register - Step 06: Input data E-Mail textbox is : " + emailAddress);
        registerPage.inputEmailNameTextbox(emailAddress);

        log.info("TC_01_User_Register - Step 07: Input data Telephone textbox is " + phoneNumber);
        registerPage.inputTelephoneTextbox(phoneNumber);

        log.info("TC_01_User_Register - Step 08: Input data Your Password textbox: " +password);
        registerPage.inputPasswordTextbox(password);

        log.info("TC_01_User_Register - Step 09: Input data Password Confirm textbox");
        registerPage.inputConfirmPasswordTextbox(password);

        log.info("TC_01_User_Register - Step 10: Click Subscribe checkbox is Yes");
        registerPage.clickSubscribeRadioButton();

        log.info("TC_01_User_Register - Step 11: Check 'I have read and agree to the Privacy Policy'");
        registerPage.clickPrivacyPolicyCheckbox();

        log.info("TC_01_User_Register - Step 12: Click Continue button");
        successPage = registerPage.clickContinueButton();

        log.info("TC_01_User_Register - Step 13: Verify 'Your Account Has Been Created!'");
        verifyEquals(successPage.getPageHeading(), "Your Account Has Been Created!");

         log.info("TC_01_User_Register - Step 14: Verify content text of Success Page" );
        verifyTrue(successPage.getSuccessContent().contains("Congratulations! Your new account has been successfully created!"));

        log.info("TC_01_User_Register - Step 15");
        verifyEquals(successPage.getPageCurrentUrl(driver), "https://ecommerce-playground.lambdatest.io/index.php?route=account/success");
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
