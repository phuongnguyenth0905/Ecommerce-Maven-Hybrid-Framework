package com.ecommerce.account;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AccountAddressBookPageObject;
import pageObjects.AddAddressPageObject;
import pageObjects.MyAccountPageObject;
import utilitiesConfig.FakerDataHelper;

public class Account_AddAddress extends BaseTest {
    WebDriver driver;
    MyAccountPageObject myAccountPage;
    AddAddressPageObject addAddressPage;
    AccountAddressBookPageObject accountAddressBookPage;
    FakerDataHelper fakerData;
    String firstName, lastName, companyName, address, city, postCode, country, state, defaultAddress, region;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void beforeClass(String browserName, String urlValue) {
        driver = getBrowserDriver(browserName, urlValue);
        myAccountPage = loginUser("xdutg78sd@gmail.com", "123456789");

        fakerData = FakerDataHelper.getData();
        firstName = fakerData.getFirstName();
        lastName = fakerData.getLastName();
        companyName = fakerData.getCompanyName();
        address = fakerData.getAddress();
        city = fakerData.getCity();
        postCode = fakerData.getZipCode();
        state = fakerData.getState();
        country = "Jamaica";
        region = "Portland Parish";
    }

    @Test
    public void AccountDashboard_01() {
        log.info("Add Address Account - Step 01: Open Add Address Account Link");
        accountAddressBookPage = myAccountPage.openSidebarPageByName("Address Book", AccountAddressBookPageObject.class);

        log.info("Add Address Account - Step 02 Click New Address button");
        addAddressPage = accountAddressBookPage.clickNewAddressButton();

        log.info("Add Address Account - Step 03: Verify Edit Account page is displayed");
        verifyTrue(addAddressPage.isAddAddressPageDisplayed("Add Address"));

        log.info("Add Address Account - Step 04: Enter 'First Name' in the textbox.");
        addAddressPage.inputToFirstNameTextbox(firstName);

        log.info("Add Address Account - Step 05: Enter 'Last Name' in the textbox.");
        addAddressPage.inputToLastNameTextbox(lastName);

        log.info("Add Address Account - Step 06: Enter 'Company Name' in the textbox.");
        addAddressPage.inputToCompanyNameTextbox(companyName);

        log.info("Add Address Account - Step 07: Enter 'Address 1' in the textbox.");
        addAddressPage.inputToAddressTextbox(address);

        log.info("Add Address Account - Step 08: Enter 'City Name' in the textbox.");
        addAddressPage.inputToCityNameTextbox(city);

        log.info("Add Address Account - Step 09: Enter 'Post Code' in the textbox.");
        addAddressPage.inputToPostCodeTextbox(postCode);

        log.info("Add Address Account - Step 10: Enter 'Country Name' in the select dropdown.");
        addAddressPage.selectCountryAccountDropdown(country);

        log.info("Add Address Account - Step 11: Enter 'Region / State' in the select dropdown.");
        addAddressPage.selectRegionAccountDropdown(region);

        log.info("Add Address Account - Step 12: Enter 'Default Address' in the radio button.");
        addAddressPage.clickDefaultAddressRadioButton();

        log.info("Add Address Account - Step 13: Verified are Required Fields Displayed");
        verifyTrue(addAddressPage.areRequiredFieldsDisplayed());

        log.info("Add Address Account - Step 14: Verified Country name");
        verifyEquals(addAddressPage.getSelectedCountry(), country);

        log.info("Add Address Account - Step 15: Verified Region of Country name");
        verifyEquals(addAddressPage.getSelectedRegion(), region);

        log.info("Add Address Account - Step 16: Click Continue button.");
        accountAddressBookPage = addAddressPage.clickContinueButton();

        log.info("Edit Account - Step 17: Verify success message displayed");
        verifyEquals(addAddressPage.getSuccessMessageText(),"Your address has been successfully added");
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
