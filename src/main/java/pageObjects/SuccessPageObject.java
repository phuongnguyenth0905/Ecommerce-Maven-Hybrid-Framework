package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CommonPageUI;
import pageUIs.SuccessPageUI;

import java.util.Collection;

public class SuccessPageObject extends BasePage {
    WebDriver driver;

    public SuccessPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageHeading() {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_PAGE_HEADING);
        return getElementText(driver, CommonPageUI.DYNAMIC_PAGE_HEADING);
    }
    public String getSuccessContent() {
        waitForElementVisible(driver, SuccessPageUI.SUCCESS_CONTENT);
        return getElementText(driver, SuccessPageUI.SUCCESS_CONTENT).trim();
    }

}
