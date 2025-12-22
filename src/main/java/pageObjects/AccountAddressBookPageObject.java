package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.AccountAddressBookPageUI;
import pageUIs.CommonPageUI;

import java.util.List;

public class AccountAddressBookPageObject extends BasePage {
    WebDriver driver;

    public AccountAddressBookPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public AddAddressPageObject clickNewAddressButton() {
        waitForElementClickable(driver, AccountAddressBookPageUI.NEW_ADDRESS_BUTTON);
        clickToElement(driver, AccountAddressBookPageUI.NEW_ADDRESS_BUTTON);
        return PageGeneratorManager.getPage(AddAddressPageObject.class,driver);
    }

    public EditAddressPageObject clickAddressActionByText(String actionText) {
        waitForElementClickable(driver, AccountAddressBookPageUI.ADDRESS_ACTION_LINK_BY_TEXT,actionText);
        clickToElement(driver, AccountAddressBookPageUI.ADDRESS_ACTION_LINK_BY_TEXT,actionText);

        return PageGeneratorManager.getPage(EditAddressPageObject.class, driver);
    }

    public  String getUpdateAddressBlockText(String address1, String postCode) {
        waitForElementVisible(driver, AccountAddressBookPageUI.ADDRESS_BLOCK, address1, postCode);
        return getElementText(driver, AccountAddressBookPageUI.ADDRESS_BLOCK, address1, postCode);
    }

    public String getSuccessMessageText() {
        waitForElementVisible(driver,CommonPageUI.DYNAMIC_SUCCESS_MESSAGE);
        return getElementText(driver,CommonPageUI.DYNAMIC_SUCCESS_MESSAGE);
    }
}
