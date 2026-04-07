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

public class TC2_LoginTest extends BaseClass {

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
            groups = {"Smoke", "Sanity"}
    )

    public void loginCorrectTest(String email, String password) {
        Log.startTestCase("loginCorrectTest");
        indexPage = new IndexPage();

        indexPage.validateLogo();
        Log.info("Verify that home page is visible");

        loginSignUpPage = indexPage.clickSignupLoginBtn();
        Assert.assertTrue(loginSignUpPage.verifyLoginTitle());
        Log.info("Verify 'Login to your account' is visible");

        homePage = loginSignUpPage.Login(email, password);
        Log.info("Enter correct email address and password");

        Assert.assertTrue(homePage.verifyUsernameLogged());
        Log.info("Verify that 'Logged in as username' is visible");

        Log.endTestCase("loginCorrectTest");
    }

    @Test(
            dataProvider = "invalidLoginData",
            dataProviderClass = DataProviders.class,
            groups = {"Smoke", "Sanity"}
    )
    public void loginInCorrectTest(String email, String password) {
        Log.startTestCase("loginInCorrectTest");
        indexPage = new IndexPage();

        indexPage.validateLogo();
        Log.info("Verify that home page is visible");

        loginSignUpPage = indexPage.clickSignupLoginBtn();
        Assert.assertTrue(loginSignUpPage.verifyLoginTitle());
        Log.info("Verify 'Login to your account' is visible");

        homePage = loginSignUpPage.Login(email, password);
        Assert.assertTrue(loginSignUpPage.incorrectLoginMsgCheck());
        Log.info("Verify that incorrect login");

        Log.endTestCase("loginInCorrectTest");
    }


}
