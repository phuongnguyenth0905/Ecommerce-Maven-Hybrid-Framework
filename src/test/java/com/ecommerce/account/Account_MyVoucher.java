package com.ecommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.MyVoucherPageObject;
import pageObjects.SuccessPageObject;
import utilitiesConfig.FakerDataHelper;

public class Account_MyVoucher extends BaseTest {
    WebDriver driver;
    MyAccountPageObject myAccountPage;
    HomePageObject homePage;
    MyVoucherPageObject myVoucherPage;
    SuccessPageObject successPage;
    FakerDataHelper fakerData;

    String recipientName, recipientEmail, messageText, amountGift;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void beforeClass(String browserName, String urlValue) {
        driver = getBrowserDriver(browserName, urlValue);
        homePage = PageGeneratorManager.getPage(HomePageObject.class, driver);
        myAccountPage = loginUser("xdutg78sd@gmail.com", "123456789");
        fakerData = FakerDataHelper.getData();
        recipientName = fakerData.getName();
        recipientEmail = fakerData.getEmail();
        messageText = "Your Gift Purchase PP123";
        amountGift = "5";

    }

    @Test
    public void Account_MyVoucher_01_Open() {
        log.info("My Voucher Account - Step 01: Open My Voucher link");
        myVoucherPage = homePage.openMyAccountHeaderItem("My voucher", MyVoucherPageObject.class);

        log.info("My Voucher Account - Step 02: Verify Edit Account page is displayed");
        verifyTrue(myVoucherPage.isAddAddressPageDisplayed("Purchase a Gift Certificate"));

    }

    @Test
    public void Account_MyVoucher_02_Validated() {
        log.info("My Voucher Account - Step 03: Click Continue Button ");
        myVoucherPage.clickContinueButton();

        log.info("My Voucher Account - Step 04: Verify Warning message agree that the gift certificates ");
        verifyEquals(myVoucherPage.getWarningMessage(), "Warning: You must agree that the gift certificates are non-refundable!");

        log.info("My Voucher Account - Step 05: Verify 'Recipient's Name must be between 1 and 64 characters!' text");
        verifyEquals( myVoucherPage.getRecipientNameErrorMessage(),"Recipient's Name must be between 1 and 64 characters!" );

//        log.info("My Voucher Account - Step 05: Verify ''text");
//        verifyEquals(myVoucherPage.getThemeErrorMessage(), "You must select a theme!" );
        log.info("My Voucher Account - Step 06: Input to 'Recipient's Name' text");
        myVoucherPage.inputToRecipientNameTextbox(recipientName);

        log.info("My Voucher Account - Step 07: Click to Continue button");
        myVoucherPage.clickContinueButton();

        log.info("My Voucher Account - Step 08:  Verify 'E-Mail Address does not appear to be valid!' text ");
        verifyEquals(myVoucherPage.getRecipientEmailErrorMessage(), "E-Mail Address does not appear to be valid!");

    }

    @Test
    public void Account_MyVoucher_03_Create_Gif_Voucher() {
        log.info("My Voucher Account - Step 09: Input to 'Recipient's Name' textbox");
        myVoucherPage.inputToRecipientNameTextbox(recipientName);

        log.info("My Voucher Account - Step 10: Input to 'Recipient's e-mail' textbox");
        myVoucherPage.inputToRecipientEmailTextbox(recipientEmail);

        log.info("My Voucher Account - Step 11: Input to 'Message' textbox");
        myVoucherPage.inputToMessageText(messageText);

        log.info("My Voucher Account - Step 12: Click choose 'Gift Certificate Theme' radion button ");
        myVoucherPage.clickGifThemeRadioButton();

        log.info("My Voucher Account - Step 13: Input to 'Amount Gift' textbox ");
        myVoucherPage.inputToAmountGift(amountGift);

        log.info("My Voucher Account - Step 14: Tick Agree checkbox");
        myVoucherPage.clickAgreeGiftCertificatesCheckbox();

        log.info("My Voucher Account - Step 15:Click Continue button ");
        successPage = myVoucherPage.clickContinueButton();

        log.info("My Voucher Account - Step 16: Verify success title");
         verifyEquals(successPage.getPageHeading(), "Purchase a Gift Certificate");

        log.info("My Voucher Account - Step 17: Verify success content text ");
        verifyTrue(successPage.getSuccessContent().contains("Thank you for purchasing a gift certificate"));
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
