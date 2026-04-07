package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.ContactUsPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.utility.Log;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC5_ContactUsFormTest extends BaseClass {

    IndexPage indexPage;
    ContactUsPage contactUsPage;

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
            dataProvider = "contactUsFormData",
            dataProviderClass = DataProviders.class,
            groups = {"Smoke"}
    )
    public void contactsUsFormTest(
            String name,
            String email,
            String subject,
            String msg,
            String file
    ) {
        Log.startTestCase("contactsUsFormTest");
        indexPage = new IndexPage();
        Assert.assertTrue(indexPage.validateLogo());
        getDriver().navigate().refresh();
        contactUsPage = indexPage.clickContactUsNav();

        Assert.assertTrue(contactUsPage.verifyContactUsPage());
        Log.info("Verify Contact Us Page");
        contactUsPage.fillForm(name, email, subject, msg);
        Log.info("Fill Form");
        contactUsPage.uploadFile(file);
        Log.info("Upload file");

        contactUsPage.clickSubmitBtn();
        Log.info("Click submit button");

        String alertMsg = contactUsPage.acceptAlertAndGetText();
        Assert.assertTrue(alertMsg.contains("Press OK"));
        Log.info("Accepted alert");

        Assert.assertTrue(contactUsPage.verifyContactUsSubmitted());
        Log.info("Verify Contact Us Submitted");

        Log.endTestCase("contactsUsFormTest");
    }


}
