package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.CommonPageUI;
import pageUIs.MyVoucherPageUI;

public class MyVoucherPageObject extends BasePage {
    WebDriver driver;

    public MyVoucherPageObject(WebDriver driver) {

        this.driver = driver;
    }

    public boolean isAddAddressPageDisplayed(String expectedTitle) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_PAGE_HEADING);
        return getElementText(driver, CommonPageUI.DYNAMIC_PAGE_HEADING).equals(expectedTitle);
    }

    public String getWarningMessage() {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_PAGE_HEADING);
        return getElementText(driver, CommonPageUI.DYNAMIC_WARNING_MESSAGE);
    }

    public String getRecipientNameErrorMessage() {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_PAGE_HEADING);
        return getElementText(driver, CommonPageUI.DYNAMIC_WARNING_TEXT);

    }

    public String getRecipientEmailErrorMessage() {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_PAGE_HEADING);
        return getElementText(driver, CommonPageUI.DYNAMIC_WARNING_TEXT);

    }

    public String getThemeErrorMessage() {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_PAGE_HEADING);
        return getElementText(driver, CommonPageUI.DYNAMIC_WARNING_TEXT);
    }

    public void inputToRecipientNameTextbox(String recipientNametxt) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, "to_name");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, recipientNametxt, "to_name");

    }

    public void inputToRecipientEmailTextbox(String recipientEmailtxt) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, "to_email");
        sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_NAME, recipientEmailtxt, "to_email");
    }

    public void inputToMessageText(String messageText) {
        waitForElementVisible(driver, MyVoucherPageUI.MESSAGE_TEXTBOX, "message");
        sendKeyToElement(driver, MyVoucherPageUI.MESSAGE_TEXTBOX, messageText, "message");
    }

    public void clickGifThemeRadioButton() {
        waitForElementClickable(driver, MyVoucherPageUI.GIFT_THEME_RADIOBUTTON);
        clickToElement(driver, MyVoucherPageUI.GIFT_THEME_RADIOBUTTON);
    }

    public void inputToAmountGift(String amountGift) {
        waitForElementVisible(driver, MyVoucherPageUI.AMOUNT_TEXTBOX, "amount");
        sendKeyToElement(driver, MyVoucherPageUI.AMOUNT_TEXTBOX, amountGift, "amount");
    }

    public void clickAgreeGiftCertificatesCheckbox() {
        waitForElementClickable(driver, MyVoucherPageUI.AGREE_TEXTBOX);
        clickToElement(driver, MyVoucherPageUI.AGREE_TEXTBOX);
    }

    public SuccessPageObject clickContinueButton() {
        waitForElementClickable(driver, MyVoucherPageUI.CONTINUE_BUTTON);
        clickToElement(driver, MyVoucherPageUI.CONTINUE_BUTTON);
        return PageGeneratorManager.getPage(SuccessPageObject.class, driver);
    }


}
