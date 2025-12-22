package com.ecommerce.account;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyAccount_Dashboard extends BaseTest {
WebDriver driver;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void beforeClass() {
        driver = getDriver();

    }

    @Test
    public void AccountDashboard_01() {
    }
    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
