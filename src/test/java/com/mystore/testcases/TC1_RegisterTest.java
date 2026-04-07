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

public class TC1_RegisterTest extends BaseClass {

    IndexPage indexPage;
    LoginSignUpPage loginSignUpPage;
    AccountCreationPage accountCreationPage;
    AccountCreatedPage accountCreatedPage;
    HomePage homePage;
    AccountDeletedPage accountDeletedPage;

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
            dataProvider = "accountCreationData",
            dataProviderClass = DataProviders.class,
            groups = {"Regression"})

    public void registerAndDeleteTest(
            String email,
            String name,
            String gender,
            String password,
            String day,
            String month,
            String year,
            String newsletter,
            String specialOffers,

            String firstName,
            String lastName,
            String company,
            String address1,
            String address2,
            String country,
            String state,
            String city,
            String zipcode,
            String mobileNumber
    ) {
        Log.startTestCase("registerAndDelete");
        indexPage = new IndexPage();
        Assert.assertTrue(indexPage.validateLogo());
        getDriver().navigate().refresh();

        Log.info("Click on 'Signup / Login' button");
        loginSignUpPage = indexPage.clickSignupLoginBtn();
        Assert.assertTrue(loginSignUpPage.verifySignUpTitle());
        accountCreationPage = loginSignUpPage.Signup(name, email);

        Log.info("Verify that 'ENTER ACCOUNT INFORMATION' is visible");
        getDriver().navigate().refresh();
        Assert.assertTrue(accountCreationPage.verifyAccountCreationPage());

        accountCreationPage.selectGender(gender);
        accountCreationPage.inputPassword(password);
        accountCreationPage.selectDay(day);
        accountCreationPage.selectMonth(month);
        accountCreationPage.selectYear(year);

        Log.info("Select checkbox");
        accountCreationPage.setNewsletter(newsletter);
        accountCreationPage.setSpecialOffers(specialOffers);

        accountCreationPage.inputFirstname(firstName);
        accountCreationPage.inputLastname(lastName);
        accountCreationPage.inputCompany(company);
        accountCreationPage.inputAddress1(address1);
        accountCreationPage.inputAddress2(address2);
        accountCreationPage.selectCountry(country);
        accountCreationPage.inputState(state);
        accountCreationPage.inputCity(city);
        accountCreationPage.inputZipcode(zipcode);
        accountCreationPage.inputPhoneNumber(mobileNumber);

        accountCreatedPage = accountCreationPage.clickCreateAccountBtn();
        Assert.assertTrue(accountCreatedPage.validateCreatedTitle());
        Assert.assertTrue(accountCreatedPage.validateCreatedMessage());
        Log.info("Verify that 'ACCOUNT CREATED!' is visible");

        homePage = accountCreatedPage.clickContinueBtn();
        Log.info("Click 'Continue' button");

        Assert.assertTrue(homePage.verifyUsernameLogged());
        Log.info("Verify that 'Logged in as username' is visible");
        Assert.assertTrue(homePage.verifyLogoutNav());
        Assert.assertTrue(homePage.verifyDeleteAccountNav());

        accountDeletedPage = homePage.clickDeleteAccountBtn();
        Log.info("Click 'Delete Account' button");

        Assert.assertTrue(accountDeletedPage.verifyDeletedTitle());
        Log.info(" Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button");
        homePage = accountDeletedPage.clickContinueBtn();
        Assert.assertTrue(indexPage.validateLogo());

        Log.endTestCase("registerAndDelete");
    }

}
