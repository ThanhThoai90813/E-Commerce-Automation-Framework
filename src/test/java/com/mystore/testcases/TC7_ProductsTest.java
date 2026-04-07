package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.*;
import com.mystore.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC7_ProductsTest extends BaseClass {

    IndexPage indexPage;
    AllProductPage allProductPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;
    HomePage homePage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke","Sanity","Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke","Sanity","Regression"})
    public void tearDown() {
        getDriver().quit();
    }

    @Test(groups = {"Smoke"})
    public void verifyAllProductToDetailTest() {
        Log.startTestCase("verifyAllProductToDetail");

        indexPage = new IndexPage();
        Assert.assertTrue(indexPage.validateLogo());
        getDriver().navigate().refresh();
        allProductPage = indexPage.clickProductsNav();

        Assert.assertTrue(allProductPage.verifyProductPage());
        Log.info("Verify user is navigated to ALL PRODUCTS page successfully");
        Assert.assertTrue(allProductPage.isProductAvailable());
        Log.info("Verify the products list is visible");
        productDetailPage = allProductPage.clickViewFirstProduct();

        Log.info("Verify that detail detail is visible: product name, category, price, availability, condition, brand");
        Assert.assertTrue(productDetailPage.verifyProductName());
        Assert.assertTrue(productDetailPage.verifyProductImg());
        Assert.assertTrue(productDetailPage.verifyProductCategory());
        Assert.assertTrue(productDetailPage.verifyProductPrice());
        Assert.assertTrue(productDetailPage.verifyProductAvailability());
        Assert.assertTrue(productDetailPage.verifyProductCondition());
        Assert.assertTrue(productDetailPage.verifyProductBrand());
        Log.info("Verify the products detail is visible");

        Log.endTestCase("verifyAllProductToDetail");

    }

    @Test(groups = {"Smoke"})
    public void verifyProductQuantityInCartTest(){
        Log.startTestCase("verifyProductQuantityTest");

        indexPage = new IndexPage();
        homePage = new HomePage();
        Assert.assertTrue(indexPage.validateLogo());
        getDriver().navigate().refresh();
        productDetailPage = homePage.clickViewProduct();
        Log.info("Verify that detail detail is visible: product name, category, price, availability, condition, brand");
        Assert.assertTrue(productDetailPage.verifyProductName());
        Assert.assertTrue(productDetailPage.verifyProductImg());
        Assert.assertTrue(productDetailPage.verifyProductCategory());
        Assert.assertTrue(productDetailPage.verifyProductPrice());
        Assert.assertTrue(productDetailPage.verifyProductAvailability());
        Assert.assertTrue(productDetailPage.verifyProductCondition());
        Assert.assertTrue(productDetailPage.verifyProductBrand());
        Log.info("Verify the products detail is visible");

        Log.info("Add to cart");
        productDetailPage.enterQuantity("4");
        productDetailPage.clickOnAddToCart();
        Assert.assertTrue(productDetailPage.validateAddToCart());
        cartPage = productDetailPage.clickViewCartText();

        Assert.assertTrue(cartPage.verifyCartPage());
        cartPage.verifyProductQuantity(0, 4);
        Log.info("Verify that product is displayed in cart page with exact quantity");
        cartPage.verifyProductPriceQuantityTotal();

        Log.endTestCase("verifyProductQuantityTest");

    }

}
