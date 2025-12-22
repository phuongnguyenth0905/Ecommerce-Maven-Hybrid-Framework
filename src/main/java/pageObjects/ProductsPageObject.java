package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.CommonPageUI;
import pageUIs.ProductsPageUI;
import utilitiesConfig.RandomDataUtils;

public class ProductsPageObject extends BasePage {
    WebDriver driver;

    public ProductsPageObject(WebDriver driver) {

        this.driver = driver;
    }

    public void clickProductAction(String productName, String actionProduct) {
        scrollToElement(driver, CommonPageUI.DYNAMIC_PRODUCT_ACTION_BUTTON_BY_NAME_AND_TYPE, productName, actionProduct);
        RandomDataUtils.sleepInSecond(0.5);
        hoverToElement(driver, CommonPageUI.DYNAMIC_PRODUCT_ACTION_BUTTON_BY_NAME_AND_TYPE, productName, actionProduct);
        RandomDataUtils.sleepInSecond(0.5);
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_PRODUCT_ACTION_BUTTON_BY_NAME_AND_TYPE, productName, actionProduct);
        RandomDataUtils.sleepInSecond(1);
        clickToElementByJS(driver, CommonPageUI.DYNAMIC_PRODUCT_ACTION_BUTTON_BY_NAME_AND_TYPE, productName, actionProduct);
        RandomDataUtils.sleepInSecond(2);
    }
    public void addToCart(String productName) {
        clickProductAction(productName, "btn-cart");
    }

    public void addToWishlist(String productName) {
        clickProductAction(productName, "btn-wishlist");
    }

    public void compareProduct(String productName) {
        clickProductAction(productName, "btn-compare");
    }

    public void openQuickView(String productName) {
        clickProductAction(productName, "btn-quick-view");
    }


    public void closeQuickView() {
        waitForElementClickable(driver, ProductsPageUI.CLOSE_QUICK_VIEW_ICON);
        clickToElement(driver, ProductsPageUI.CLOSE_QUICK_VIEW_ICON);


    }
    public void clickToCartIcon() {
        waitForElementClickable(driver, ProductsPageUI.CART_ICON);
        clickToElement(driver, ProductsPageUI.CART_ICON);
    }

    public CheckOutPageObject clickCartButton(String clickButton) {
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_SHOPING_TO_CART_BUTTON, clickButton);
        clickToElement(driver, CommonPageUI.DYNAMIC_SHOPING_TO_CART_BUTTON, clickButton);
        return PageGeneratorManager.getPage(CheckOutPageObject.class, driver);
    }

}
