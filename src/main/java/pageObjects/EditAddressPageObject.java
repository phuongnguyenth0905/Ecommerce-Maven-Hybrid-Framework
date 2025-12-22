package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.AddAddressPageUI;
import pageUIs.CommonPageUI;
import pageUIs.EditAccounPageUI;

public class EditAddressPageObject extends BasePage {
    WebDriver driver;

    public EditAddressPageObject(WebDriver driver) {

        this.driver = driver;
    }

    public boolean isEditAddressPageDisplayed(String expectedTitle) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_PAGE_HEADING);
        return getElementText(driver, CommonPageUI.DYNAMIC_PAGE_HEADING).equals(expectedTitle);

    }

    public void inputToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"firstname");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, firstName,"firstname");

    }

    public void inputToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"lastname");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, lastName,"lastname");

    }

    public void inputToCompanyNameTextbox(String companyName) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"company");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, companyName,"company");

    }

    public void inputToAddress1Textbox(String address) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"address_1");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, address,"address_1");

    }
    public void inputToAddress2Textbox(String address) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"address_2");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, address,"address_2");

    }

    public void inputToCityNameTextbox(String city) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"city");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, city,"city");

    }

    public void inputToPostCodeTextbox(String postCode) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"postcode");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, postCode,"postcode");

    }

    public void selectCountryAccountDropdown(String valueItem) {

        waitForElementVisible(driver, AddAddressPageUI.COUNTRY_DROPDOWN,valueItem);
        selectItemInDropdown(driver, AddAddressPageUI.COUNTRY_DROPDOWN,valueItem);
    }

    public void selectRegionAccountDropdown(String valueItem) {
        waitForElementVisible(driver, AddAddressPageUI.REGION_DROPDOWN,valueItem);
        selectItemInDropdown(driver, AddAddressPageUI.REGION_DROPDOWN,valueItem,"zone_id");

    }

    public void clickDefaultAddressRadioButton() {
        clickToElement(driver, AddAddressPageUI.DEFAULT_ADDRESS_RADIOBUTTON);
    }



    public AccountAddressBookPageObject clickContinueButton() {
        waitForElementClickable(driver, AddAddressPageUI.CONTINUE_BUTTON);
        clickToElement(driver, AddAddressPageUI.CONTINUE_BUTTON);
        return PageGeneratorManager.getPage(AccountAddressBookPageObject.class,driver);

    }


    public Object getSelectedCountry() {
        waitForElementVisible(driver, AddAddressPageUI.COUNTRY_DROPDOWN);
        return getSelectedItemInDropdown(driver, AddAddressPageUI.COUNTRY_DROPDOWN);

    }

    public Object getSelectedRegion() {
        waitForElementVisible(driver, AddAddressPageUI.REGION_DROPDOWN);
        return getSelectedItemInDropdown(driver, AddAddressPageUI.REGION_DROPDOWN);

    }

    public MyAccountPageObject clickBackButtonGoDashboard() {
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_BACK_BUTTON);
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_BACK_BUTTON);
        return PageGeneratorManager.getPage(MyAccountPageObject.class, driver);
    }

    public String getSuccessMessageText() {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_SUCCESS_MESSAGE);
        return getElementText(driver, CommonPageUI.DYNAMIC_SUCCESS_MESSAGE);
    }
}
