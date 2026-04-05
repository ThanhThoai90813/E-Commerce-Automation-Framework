package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.utility.Log;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC0_IndexPageTest extends BaseClass {

    IndexPage indexPage;

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
    public void indexPageTest() {
        Log.startTestCase("indexPageTest");
        indexPage = new IndexPage();

        indexPage.validateLogo();
        Log.info("Verify that home page is visible");


        Log.endTestCase("indexPageTest");
    }

}
