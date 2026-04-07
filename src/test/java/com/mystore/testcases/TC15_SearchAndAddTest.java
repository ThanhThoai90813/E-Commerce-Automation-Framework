package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.*;
import com.mystore.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC15_SearchAndAddTest extends BaseClass {

    IndexPage indexPage;
    AllProductPage allProductPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;
    LoginSignUpPage loginSignUpPage;
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

    @Test(
            dataProvider = "getSearchData",
            dataProviderClass = DataProviders.class,
            groups = {"Sanity"})
    public void searchAndAddProductToCartTest(String searchKey) {
        Log.startTestCase("searchAndAddProductToCartTest");

        indexPage = new IndexPage();
        Assert.assertTrue(indexPage.validateLogo());
        getDriver().navigate().refresh();
        Assert.assertTrue(indexPage.verifyProductsIcon());
        allProductPage = indexPage.clickProductsNav();

        Assert.assertTrue(allProductPage.verifyProductPage());
        Log.info("Verify user is navigated to ALL PRODUCTS page successfully");
        Assert.assertTrue(allProductPage.isProductAvailable());
        Log.info("Verify the products list is visible");

        allProductPage.InputSearchKey(searchKey);
        allProductPage.clickSearchBtn();
        Log.info("Click Search Button");

        Assert.assertTrue(allProductPage.verifySearchProduct());
        Log.info("Verify 'SEARCHED PRODUCTS' is visible");

        boolean result = allProductPage.isProductAvailable();
        if (result) {
            productDetailPage = allProductPage.clickViewFirstProduct();
            Assert.assertNotNull(productDetailPage);
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

            //Login
            Log.info("Click on 'Signup / Login' button");
            loginSignUpPage = indexPage.clickSignupLoginBtn();
            Assert.assertTrue(loginSignUpPage.verifyLoginTitle());
            Log.info("Verify 'Login to your account' is visible");
            homePage = loginSignUpPage.Login("thanhthoai13@gmail.com", "123@@");
            Log.info("Enter correct email address and password");
            Assert.assertTrue(homePage.verifyUsernameLogged());
            Log.info("Verify that 'Logged in as username' is visible");

            //Go To Cart Page
            cartPage = homePage.clickCartNav();
            Assert.assertTrue(cartPage.verifyCartPage());

            Log.info("Verify product quantity");

        } else {
            Assert.fail("Không tìm thấy sản phẩm nào");
        }

        Log.endTestCase("searchAndAddProductToCartTest");
    }

}
