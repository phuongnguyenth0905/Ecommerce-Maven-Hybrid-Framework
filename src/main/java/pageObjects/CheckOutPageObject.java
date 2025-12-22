package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.CheckOutPageUI;
import pageUIs.CommonPageUI;

public class CheckOutPageObject extends BasePage {
    WebDriver driver;

    public CheckOutPageObject(WebDriver driver) {

        this.driver = driver;
    }

    public void removeProduct(String productName) {
        waitForElementClickable(driver, CheckOutPageUI.REMOVE_BTN_IN_ROW, productName);
        clickToElement(driver, CheckOutPageUI.REMOVE_BTN_IN_ROW, productName);
    }

    public void updateProduct(String productName, String qty) {
        waitForElementClickable(driver, CheckOutPageUI.UPDATE_BTN_IN_ROW, productName);
        sendKeyToElement(driver, CheckOutPageUI.QTY_INPUT_BY_PRODUCT, qty, productName);
        clickToElement(driver, CheckOutPageUI.UPDATE_BTN_IN_ROW, productName);
    }

    public boolean getWarningMessage() {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_WARNING_MESSAGE);
        return isElementDisplayed(driver, CommonPageUI.DYNAMIC_WARNING_MESSAGE);
    }

    public boolean getSuccessMessageText() {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, CommonPageUI.DYNAMIC_SUCCESS_MESSAGE);
    }

    public ProductDetailPageObject clickProductName(String productName) {
        waitForElementClickable(driver, CheckOutPageUI.PRODUCT_NAME, productName);
        clickToElement(driver, CheckOutPageUI.PRODUCT_NAME, productName);
        return PageGeneratorManager.getPage(ProductDetailPageObject.class, driver);
    }
}
