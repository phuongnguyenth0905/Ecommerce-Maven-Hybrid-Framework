package com.ecommerce.account;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Account_Notification extends BaseTest {
WebDriver driver;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void beforeClass() {
        driver = getDriver();

    }

    @Test
    public void AccountDashboard_01() {
    }

}
