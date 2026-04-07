package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AllProductPage;
import com.mystore.pageobjects.CartPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.ProductDetailPage;
import com.mystore.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC9_SubscriptionTest extends BaseClass {

    IndexPage indexPage;
    CartPage cartPage;

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
    public void verifySubscriptionInHomePageTest() {
        Log.startTestCase("verifySubscriptionInHomePageTest");

        indexPage = new IndexPage();
        Assert.assertTrue(indexPage.validateLogo());
        getDriver().navigate().refresh();
        indexPage.inputSubField("subEmail@gmail.com");
        indexPage.clickSubBtn();
        Log.info("Click sub button");

        Assert.assertTrue(indexPage.verifySuccessSub());
        Log.info("Verify success message 'You have been successfully subscribed!' is visible");

        Log.endTestCase("verifySubscriptionInHomePageTest");
    }

    @Test(groups = {"Smoke"})
    public void verifySubscriptionInCartPageTest() {
        Log.startTestCase("verifySubscriptionInCartPageTest");

        indexPage = new IndexPage();
        Assert.assertTrue(indexPage.validateLogo());
        getDriver().navigate().refresh();
        cartPage = indexPage.clickCartNav();
        Assert.assertTrue(cartPage.verifyCartPage());
        Log.info("Verify Cart Page");
        Assert.assertTrue(cartPage.verifySubTitle());

        cartPage.inputSubField("subEmail@gmail.com");
        cartPage.clickSubBtn();
        Log.info("Click sub button");

        Assert.assertTrue(indexPage.verifySuccessSub());
        Log.info("Verify success message 'You have been successfully subscribed!' is visible");

        Log.endTestCase("verifySubscriptionInCartPageTest");
    }

}
