package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginSignUpPage;
import com.mystore.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC4_RegisterFailedTest extends BaseClass {

    IndexPage indexPage;
    LoginSignUpPage loginSignUpPage;
    AccountCreationPage accountCreationPage;

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
            dataProvider = "signupFailedData",
            dataProviderClass = DataProviders.class,
            groups = {"Smoke"}
    )
    public void signupFailedTest(String name, String email) {
        Log.startTestCase("signupFailedTest");
        indexPage = new IndexPage();

        indexPage.validateLogo();
        Log.info("Verify that home page is visible");
        getDriver().navigate().refresh();
        loginSignUpPage = indexPage.clickSignupLoginBtn();
        Assert.assertTrue(loginSignUpPage.verifySignUpTitle());
        Log.info("Verify 'New User Signup!' is visible");

        accountCreationPage = loginSignUpPage.Signup(name, email);
        Assert.assertTrue(loginSignUpPage.incorrectSignUpMsgCheck());
        Log.info("Verify that incorrect signup");

        Log.endTestCase("signupFailedTest");
    }

}
