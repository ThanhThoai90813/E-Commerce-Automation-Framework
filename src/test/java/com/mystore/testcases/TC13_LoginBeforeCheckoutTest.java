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

public class TC13_LoginBeforeCheckoutTest extends BaseClass {

    IndexPage indexPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;
    HomePage homePage;
    LoginSignUpPage loginSignUpPage;
    PaymentPage paymentPage;
    CheckOutPage checkOutPage;

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
            groups = {"Regression"})
    public void loginBeforeCheckoutTest(
            String email,
            String password
    ) {
        Log.startTestCase("loginBeforeCheckoutTest");

        indexPage = new IndexPage();
        Assert.assertTrue(indexPage.validateLogo());
        getDriver().navigate().refresh();


        //Login
        Log.info("Click on 'Signup / Login' button");
        loginSignUpPage = indexPage.clickSignupLoginBtn();
        Assert.assertTrue(loginSignUpPage.verifyLoginTitle());
        Log.info("Verify 'Login to your account' is visible");
        homePage = loginSignUpPage.Login(email, password);
        Log.info("Enter correct email address and password");
        Assert.assertTrue(homePage.verifyUsernameLogged());
        Log.info("Verify that 'Logged in as username' is visible");

        //Buy product
        productDetailPage = homePage.clickViewProduct();
        Log.info("Verify that detail detail is visible: product name, category, price, availability, condition, brand");
        getDriver().navigate().refresh();
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
        cartPage.clickCheckOutBtn();

        //Verify CheckOutPage
        checkOutPage = new CheckOutPage();
        checkOutPage.verifyBothAddresses();
        double expectedTotal = checkOutPage.calculateTotalFromItems();
        double actualTotal = checkOutPage.getDisplayedTotal();
        Assert.assertEquals(actualTotal, expectedTotal, "Total price mismatch!");
        checkOutPage.verifyTotal();

        checkOutPage.inputOrderMsg("Giao vào giờ hành chính");
        paymentPage = new PaymentPage();
        paymentPage = checkOutPage.clickPlaceOrders();
        Log.info("Click 'Pay and Confirm Order' button");
        //Payment
        paymentPage.typeToCardField("abc123");
        paymentPage.typeToNumberField("138133123");
        paymentPage.typeToCvcField("133");
        paymentPage.typeToExpirationMothField("11");
        paymentPage.typeToExpirationYearField("2028");
        paymentPage.clickConfirmOrder();
        boolean result3 = paymentPage.validateOrderPlaced();
        Assert.assertTrue(result3);

//        Log.info("Delete Account");
//        accountDeletedPage = homePage.clickDeleteAccountBtn();
//        Log.info("Click 'Delete Account' button");
//        Assert.assertTrue(accountDeletedPage.verifyDeletedTitle());
//        Log.info(" Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button");
//        homePage = accountDeletedPage.clickContinueBtn();
//        Assert.assertTrue(indexPage.validateLogo());

        Log.endTestCase("loginBeforeCheckoutTest");
    }

}
