package com.ecommerce.account;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AccountAddressBookPageObject;
import pageObjects.EditAddressPageObject;
import pageObjects.MyAccountPageObject;
import utilitiesConfig.FakerDataHelper;

public class Account_AddressBook extends BaseTest {
    WebDriver driver;
    AccountAddressBookPageObject accountAddressBookPage;
    MyAccountPageObject myAccountPage;
    EditAddressPageObject editAddressPage;
    FakerDataHelper fakerData;
    String firstName, lastName, companyName, address1, address2, city, postCode, country, state, defaultAddress, region;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void beforeClass(String browserName, String urlValue) {
        driver = getBrowserDriver(browserName, urlValue);
        myAccountPage = loginUser("xdutg78sd@gmail.com", "123456789");

        fakerData = FakerDataHelper.getData();
        firstName = fakerData.getFirstName();
        lastName = fakerData.getLastName();
        companyName = fakerData.getCompanyName();
        address1 = fakerData.getAddress();
        address2=fakerData.getAddress();
        city = fakerData.getCity();
        postCode = fakerData.getZipCode();
        state = fakerData.getState();
        country = "China";
        region = "Hebei";
    }

    @Test(groups = "Open Address Book of Account User")
    public void Account_Address_01() {
        log.info("Address Account - Step 01: Open Add Address Account Link");
        accountAddressBookPage = myAccountPage.openSidebarPageByName("Address Book", AccountAddressBookPageObject.class);

    }

    @Test(groups = "Delete Address Book of Account User")
    public void DeleteAddressBook() {
        log.info("Address Account - Delete AddressBook - Step 02: Delete Address Book Entries");
        accountAddressBookPage.clickAddressActionByText("Delete");

        log.info("Address Account - Delete AddressBook - Step 03: Verify message after delete Address");
        verifyEquals(accountAddressBookPage.getSuccessMessageText(),"Your address has been successfully deleted");

    }

    @Test
    public void EditAddressBook() {
        log.info("Address Account - Edit Address Account - Step 01 : Open Edit Address Book ");
        editAddressPage = accountAddressBookPage.clickAddressActionByText("Edit");

        log.info("Address Account - Edit Address Account - Step 02: Verify Edit Account page is displayed");
        verifyTrue(editAddressPage.isEditAddressPageDisplayed("Edit Address"));

        log.info("Address Account - Edit Address Account - Step 03: Enter 'First Name' in the textbox.");
        editAddressPage.inputToFirstNameTextbox(firstName);

        log.info("Address Account - Edit Address Account - Step 04: Enter 'Last Name' in the textbox.");
        editAddressPage.inputToLastNameTextbox(lastName);

        log.info("Address Account - Edit Address Account - Step 05: Enter 'Company Name' in the textbox.");
        editAddressPage.inputToCompanyNameTextbox(companyName);

        log.info("Address Account - Edit Address Account - Step 06: Enter 'Address 1' in the textbox.");
        editAddressPage.inputToAddress1Textbox(address1);

        log.info("Address Account - Edit Address Account - Step 07: Enter 'Address 2' in the textbox.");
        editAddressPage.inputToAddress2Textbox(address2);

        log.info("Address Account - Edit Address Account - Step 08: Enter 'City Name' in the textbox.");
        editAddressPage.inputToCityNameTextbox(city);

        log.info("Address Account - Edit Address Account - Step 09: Enter 'Post Code' in the textbox.");
        editAddressPage.inputToPostCodeTextbox(postCode);

        log.info("Address Account - Edit Address Account - Step 10: Enter 'Country Name' in the select dropdown.");
        editAddressPage.selectCountryAccountDropdown(country);

        log.info("Address Account - Edit Address Account - Step 11: Enter 'Region / State' in the select dropdown.");
        editAddressPage.selectRegionAccountDropdown(region);

        log.info("Address Account - Edit Address Account - Step 12: Enter 'Default Address' in the radio button.");
        editAddressPage.clickDefaultAddressRadioButton();


        log.info("Address Account - Edit Address Account - Step 13: Click Continue button.");
        accountAddressBookPage = editAddressPage.clickContinueButton();

        log.info("Address Account - Edit Account - Step 14: Verify success message displayed");
        verifyEquals(editAddressPage.getSuccessMessageText(),"Your address has been successfully updated");

        log.info("Address Account - Edit Address Account - Step 15: Verified are  Fields Displayed after update");
        String actualAddress = accountAddressBookPage.getUpdateAddressBlockText(address1, postCode);

        verifyTrue(actualAddress.contains(firstName));
        verifyTrue(actualAddress.contains(lastName));
        verifyTrue(actualAddress.contains(companyName));
        verifyTrue(actualAddress.contains(address1));
        verifyTrue(actualAddress.contains(address2));
        verifyTrue(actualAddress.contains(city));
       verifyTrue(actualAddress.contains(postCode));
        verifyTrue(actualAddress.contains(country));
        verifyTrue(actualAddress.contains(region));

        log.info("Address Account - Edit Address Account - Step 16: Go Back Button from Dashboard My Account");
      myAccountPage= editAddressPage.clickBackButtonGoDashboard();
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
