package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginSignUpPage;
import com.mystore.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC3_LogoutTest extends BaseClass {

    IndexPage indexPage;
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
            dataProvider = "validLoginData",
            dataProviderClass = DataProviders.class,
            groups = {"Smoke"}
    )
    public void logOutTest(String email, String password) {
        Log.startTestCase("logOutTest");

        indexPage = new IndexPage();
        indexPage.validateLogo();
        Log.info("Verify that home page is visible");
        getDriver().navigate().refresh();
        loginSignUpPage = indexPage.clickSignupLoginBtn();
        Assert.assertTrue(loginSignUpPage.verifyLoginTitle());
        Log.info("Verify 'Login to your account' is visible");

        homePage = loginSignUpPage.Login(email, password);
        Log.info("Enter correct email address and password");

        homePage.verifyUsernameLogged();
        Log.info("Verify that 'Logged in as username' is visible");
        indexPage = homePage.clickLogoutBtn();
        Assert.assertTrue(indexPage.verifyLoginBtn());
        Log.info("Verify Login Button");

        Log.endTestCase("logOutTest");
    }

}
