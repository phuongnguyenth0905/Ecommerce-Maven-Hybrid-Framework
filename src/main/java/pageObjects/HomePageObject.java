package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.CommonPageUI;
import pageUIs.HomePageUI;

public class HomePageObject  extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {

        this.driver = driver;
    }
    public <T extends BasePage> T openMyAccountHeaderItem(String itemText, Class<T> pageClass) {
        waitForElementClickable(driver, CommonPageUI.MY_ACCOUNT_HEADER_MENU);
        hoverToElement(driver, CommonPageUI.MY_ACCOUNT_HEADER_MENU);

        waitForElementClickable(driver, CommonPageUI.MY_ACCOUNT_HEADER_ITEM_BY_TEXT, itemText);
        clickToElement(driver, CommonPageUI.MY_ACCOUNT_HEADER_ITEM_BY_TEXT, itemText);

        return PageGeneratorManager.getPage(pageClass, driver);
    }


}
