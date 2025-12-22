package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class ProductDetailPageObject extends BasePage {
    WebDriver driver;

    public ProductDetailPageObject(WebDriver driver) {

        this.driver = driver;
    }

}
