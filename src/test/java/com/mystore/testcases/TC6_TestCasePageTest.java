package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.TestCasePage;
import com.mystore.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC6_TestCasePageTest extends BaseClass {

    IndexPage indexPage;
    TestCasePage testCasePage;

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
    public void verifyTestCasePageTest() {
        Log.startTestCase("verifyTestCasePage");

        indexPage = new IndexPage();

        Assert.assertTrue(indexPage.validateLogo());
        testCasePage = indexPage.clickTestCaseNav();
        Assert.assertTrue(testCasePage.verifyTestCasePageTitle());
        Log.info("Verify TestCase Page");

        Log.endTestCase("verifyTestCasePage");
    }

}
