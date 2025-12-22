package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.CommonPageUI;

public class MyAccountPageObject extends BasePage {
    WebDriver driver;

    public MyAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public <T extends BasePage> T openSidebarPageByName(String pageName, Class<T> pageClass) {
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_SIDEBAR_ITEM_BY_NAME, pageName);
        clickToElement(driver, CommonPageUI.DYNAMIC_SIDEBAR_ITEM_BY_NAME, pageName);
        return PageGeneratorManager.getPage(pageClass, driver);
    }
    public <T extends BasePage> T openSidebarMenuHome(String itemText, Class<T> pageClass) {
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_HOME_MENU_NAME);
        clickToElement(driver, CommonPageUI.DYNAMIC_HOME_MENU_NAME);

        waitForElementClickable(driver, CommonPageUI.DYNAMIC_SIDEBAR_CATEGORIES, itemText);
        clickToElement(driver, CommonPageUI.DYNAMIC_SIDEBAR_CATEGORIES, itemText);

        return PageGeneratorManager.getPage(pageClass, driver);
    }

}
