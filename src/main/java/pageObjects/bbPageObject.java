package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.CommonPageUI;

public class bbPageObject extends BasePage {
    WebDriver driver;

    public bbPageObject(WebDriver driver) {

        this.driver = driver;
    }

}
