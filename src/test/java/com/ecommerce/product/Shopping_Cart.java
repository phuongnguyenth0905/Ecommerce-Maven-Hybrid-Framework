package com.ecommerce.product;

import commons.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CheckOutPageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.ProductDetailPageObject;
import pageObjects.ProductsPageObject;

public class Shopping_Cart extends BaseTest {
    WebDriver driver;
    MyAccountPageObject myAccountPage;
    ProductsPageObject productsPage;
    CheckOutPageObject checkOutPage;
    ProductDetailPageObject productDetailPage;

    String addProduct;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void beforeClass(String browserName, String urlValue) {
        driver = getBrowserDriver(browserName, urlValue);
        myAccountPage = loginUser("xdutg78sd@gmail.com", "123456789");


    }
    @Test
    @Step("Shopping Cart Add Products")
    public void Cart_Add_01() {
        log.info("Shopping Cart - Add Product - Steps 01: Open navbar Top categories of 'Software' menu");
        productsPage = myAccountPage.openSidebarMenuHome("Software", ProductsPageObject.class);

        log.info("Shopping Cart - Add Product - Steps 02: Add Product 'HTC Touch HD' into Shopping Cart");
        productsPage.addToCart("HTC Touch HD");

        log.info("Shopping Cart - Add Product - Steps 03: Add To Wishlist 'iPod Touch' into Shopping Cart");
        productsPage.addToWishlist("iPod Touch");

        log.info("Shopping Cart - Add Product - Steps 04:  Open navbar Top categories of 'Beauty and Saloon' menu");
        myAccountPage.openSidebarMenuHome("Beauty and Saloon", ProductsPageObject.class);

        log.info("Shopping Cart - Add Product - Steps 05: Compare Product 'iMac' into Shopping Cart");
        productsPage.compareProduct("iMac");

        log.info("Shopping Cart - Add Product - Steps 06:  Open navbar Top categories of 'Mice and Trackballs' menu");
        myAccountPage.openSidebarMenuHome("Mice and Trackballs", ProductsPageObject.class);

        log.info("Shopping Cart - Add Product - Steps 07: Open QuickView 'Nikon D300' into Shopping Cart");
        productsPage.openQuickView("Nikon D300");

        log.info("Shopping Cart - Add Product - Steps 08: Add Product 'HTC Touch HD' into Shopping Cart");
        productsPage.closeQuickView();
        productsPage.refreshPage(driver);
    }

    @Test
    @Step("Product Details Shopping Cart UI")
    public void Edit_Checkout_Cart_02() {
        log.info("Shopping Cart - Product Details- Steps 01: Click Shopping Cart Icon");
        productsPage.clickToCartIcon();

        log.info("Shopping Cart - Product Details - Steps 02: Click 'Edit cart' button");
        checkOutPage = productsPage.clickCartButton("Edit cart");

        log.info("Shopping Cart - Product Details - Steps 03: Verify message warning");
        verifyTrue(checkOutPage.getWarningMessage(), "Products marked with *** are not available in the desired quantity or not in stock!");
        // checkOutPage.removeProduct("iPod Touch");
        // checkOutPage.removeProduct("HTC Touch HD");

        log.info("Shopping Cart - Product Details - Steps 04: Click update product at Quantity column");
        checkOutPage.updateProduct("HTC Touch HD", "3");

        log.info("Shopping Cart - Product Details - Steps 05: Verify message after update product");
        verifyTrue(checkOutPage.getSuccessMessageText(), "Success: You have modified your shopping cart!");
        productDetailPage = checkOutPage.clickProductName("HTC Touch HD");

    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
