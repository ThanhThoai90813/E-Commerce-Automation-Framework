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

public class TC16_AddReviewProductTest extends BaseClass {

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
            dataProvider = "getReviewData",
            dataProviderClass = DataProviders.class,
            groups = {"Smoke"})
    public void addReviewProductTest(
            String name,
            String email,
            String reviewText
            ) {
        Log.startTestCase("addReviewProductTest");

        indexPage = new IndexPage();
        Assert.assertTrue(indexPage.validateLogo());
        getDriver().navigate().refresh();
        Assert.assertTrue(indexPage.verifyProductsIcon());
        allProductPage = indexPage.clickProductsNav();

        Assert.assertTrue(allProductPage.verifyProductPage());
        Log.info("Verify user is navigated to ALL PRODUCTS page successfully");
        Assert.assertTrue(allProductPage.isProductAvailable());
        Log.info("Verify the products list is visible");

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

            //Add Review
            Assert.assertTrue(productDetailPage.verifyReviewForm());
            productDetailPage.inputReviewForm(name, email, reviewText);
            Log.info("Inputed review form");
            productDetailPage.clickSubmitReviewBtn();
            Assert.assertTrue(productDetailPage.verifySuccessReviewMsg());
            Log.info("Verify success message 'Thank you for your review.'");

        } else {
            Assert.fail("Không tìm thấy sản phẩm nào");
        }

        Log.endTestCase("addReviewProductTest");
    }
}
