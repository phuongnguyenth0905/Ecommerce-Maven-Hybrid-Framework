package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.ChangePasswordPageUI;
import pageUIs.CommonPageUI;

public class ChangePasswordPageObject  extends BasePage {
    WebDriver driver;

    public ChangePasswordPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isChangePasswordPageDisplayed(String expectedTitle) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_PAGE_HEADING);
        return getElementText(driver, CommonPageUI.DYNAMIC_PAGE_HEADING).equals(expectedTitle);
    }

    public void inputToPasswordTextbox(String passwordChange) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, "password");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, passwordChange,"password");
    }

    public void inputToPasswordConfirmTextbox(String passwordConfirm) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, "confirm");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, passwordConfirm,"confirm");

    }

    public MyAccountPageObject clickContinueButton() {
        waitForElementClickable(driver, ChangePasswordPageUI.CONTINUE_BUTTON);
        clickToElement(driver, ChangePasswordPageUI.CONTINUE_BUTTON);
        return PageGeneratorManager.getPage(MyAccountPageObject.class,driver);
    }

    public String getSuccessMessageText() {
        waitForElementClickable(driver,CommonPageUI.DYNAMIC_SUCCESS_MESSAGE);
        return getElementText(driver,CommonPageUI.DYNAMIC_SUCCESS_MESSAGE);
    }
}
