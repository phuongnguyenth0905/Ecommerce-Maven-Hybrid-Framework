package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.CommonPageUI;
import pageUIs.EditAccounPageUI;

public class EditAccountPageObject  extends BasePage {
    WebDriver driver;

    public EditAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isEditAccountPageDisplayed(String expectedTitle) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_PAGE_HEADING);
        return getElementText(driver, CommonPageUI.DYNAMIC_PAGE_HEADING).equals(expectedTitle);
    }

    public void inputToFirstNameTextbox(String editFirstName) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, "firstname");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, editFirstName,"firstname");
    }

    public void inputToLastNameTextbox(String editLastName) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, "lastname");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, editLastName,"lastname");
    }

    public void inputToEmailTextbox(String editEmailAddress) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, "email");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, editEmailAddress, "email");
    }

    public void inputToTelephoneTextbox(String editPhoneNumber) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, "telephone");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, editPhoneNumber, "telephone");
    }

    public String getFirstNameValue() {
        waitForElementVisible(driver,CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME);
        return getElementAttributeByName(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"value", "firstname");
    }

    public String getLastNameValue() {
        waitForElementVisible(driver,CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME);
        return getElementAttributeByName(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"value","lastname");
    }

    public String getTelephoneValue() {
        waitForElementVisible(driver,CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME);
        return getElementAttributeByName(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"value","telephone");
    }
    public MyAccountPageObject clickContinueButton() {
        waitForElementClickable(driver, EditAccounPageUI.CONTINUE_BUTTON);
        clickToElement(driver, EditAccounPageUI.CONTINUE_BUTTON);
        return PageGeneratorManager.getPage(MyAccountPageObject.class,driver);
    }

    public String getSuccessMessageText() {
        waitForElementVisible(driver,CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME);
        return getElementText(driver,CommonPageUI.DYNAMIC_SUCCESS_MESSAGE);
    }
}
