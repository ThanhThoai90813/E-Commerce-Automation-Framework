package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AllProductPage;
import com.mystore.pageobjects.CartPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC10_AddToCartTest extends BaseClass {
    IndexPage indexPage;
    CartPage cartPage;
    AllProductPage allProductPage;

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
    public void addToCartTest() {
        Log.startTestCase("addToCartTest");

        indexPage = new IndexPage();
        Assert.assertTrue(indexPage.validateLogo());

        //Product Page
        allProductPage = indexPage.clickProductsNav();
        Assert.assertTrue(allProductPage.verifyProductPage());
        Log.info("Verify user is navigated to ALL PRODUCTS page successfully");
        Assert.assertTrue(allProductPage.isProductAvailable());
        Log.info("Verify the products list is visible");

        //Add to Cart
        allProductPage.clickNormalAddToCartProduct1();
        allProductPage.clickContinueShopping();
        allProductPage.clickNormalAddToCartProduct1();
        allProductPage.clickContinueShopping();
        allProductPage.clickAddToCartProduct2Overlay();
        Log.info("Add to cart successfully");

        //Cart Page
        Assert.assertTrue(allProductPage.verifyAddedCartMsg());
        cartPage = allProductPage.clickViewCart();
        Assert.assertTrue(cartPage.verifyCartPage());
        Assert.assertTrue(cartPage.verifyNumberOfProducts(1));
        cartPage.verifyProductPriceQuantityTotal();
        Log.info("Verify price, quantity and total price");


        Log.endTestCase("addToCartTest");

    }

}
