package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.CommonPageUI;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, "firstname");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, firstName, "firstname");
    }

    public void inputLastNameTextbox(String lastName) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, "lastname");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, lastName, "lastname");
    }

    public void inputEmailNameTextbox(String emailAddress) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"email");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, emailAddress,"email");
    }

    public void inputTelephoneTextbox(String phoneNumber) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"telephone");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, phoneNumber,"telephone");
    }

    public void inputPasswordTextbox(String password) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"password");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, password,"password");
        
    }

    public void inputConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME,"confirm");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, confirmPassword,"confirm");
    }

    public void clickSubscribeRadioButton() {
        waitForElementClickable(driver, RegisterPageUI.DYNAMIC_RADIO_BUTTON,"Yes");
        clickToElement(driver, RegisterPageUI.DYNAMIC_RADIO_BUTTON,"Yes");
    }

    public void clickPrivacyPolicyCheckbox() {
        waitForElementClickable(driver, RegisterPageUI.AGREE_CHECKBOX);
        clickToElement(driver, RegisterPageUI.AGREE_CHECKBOX);
    }

    public SuccessPageObject clickContinueButton() {
        waitForElementClickable(driver, RegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
        return PageGeneratorManager.getPage(SuccessPageObject.class, driver);
    }
}
