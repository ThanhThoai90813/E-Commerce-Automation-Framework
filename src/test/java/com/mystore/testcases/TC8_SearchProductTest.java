package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AllProductPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.ProductDetailPage;
import com.mystore.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC8_SearchProductTest extends BaseClass {

    IndexPage indexPage;
    AllProductPage allProductPage;
    ProductDetailPage productDetailPage;

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
            groups = {"Smoke"})
    public void searchProductTest(String searchKey) {
        Log.startTestCase("searchProductTest");

        indexPage = new IndexPage();
        Assert.assertTrue(indexPage.validateLogo());
        getDriver().navigate().refresh();
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
        } else {
            Assert.fail("Không tìm thấy sản phẩm nào");
        }

        Log.endTestCase("searchProductTest");
    }
}
