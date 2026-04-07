package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//*[@id=\"header\"]//a/img")
    WebElement myStoreLogo;

    @FindBy(xpath = "//*[@id='slider-carousel']//h1")
    WebElement carouselTitle;

    //Menu Items
    @FindBy(xpath = "//a[contains(text(),'Home')]")
    WebElement homeNav;
    @FindBy(xpath = "//a[contains(text(),'Products')]")
    WebElement productsNav;
    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    WebElement cartNav;
    @FindBy(xpath = "//a[contains(text(),'Signup / Login')]")
    WebElement signupLoginNav;
    @FindBy(xpath = "//a[contains(text(),'Test Case')]")
    WebElement testcaseNav;
    @FindBy(xpath = "//a[contains(text(),'API Testing')]")
    WebElement apiTestingNav;
    @FindBy(xpath = "//a[contains(text(),'Video')]")
    WebElement videoNav;
    @FindBy(xpath = "//a[contains(text(),'Contact us')]")
    WebElement contactUsNav;

    //Footer
    @FindBy(id = "susbscribe_email")
    WebElement subscriptField;
    @FindBy(id = "subscribe")
    WebElement subscriptBtn;
    @FindBy(id = "success-subscribe")
    WebElement successSubMeg;


    public IndexPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean validateLogo() {
        action.fluentWait(getDriver(), myStoreLogo, 5);
        return action.isDisplayed(getDriver(), myStoreLogo);
    }

    public boolean verifyProductsIcon() {
        action.fluentWait(getDriver(), productsNav, 5);
        return action.isDisplayed(getDriver(), productsNav);
    }

    public boolean getCarouselTitle() {
        return action.isDisplayed(getDriver(), carouselTitle);
    }

    public LoginSignUpPage clickSignupLoginBtn() {
        action.click(getDriver(), signupLoginNav);
        return new LoginSignUpPage();
    }

    public boolean verifyLoginBtn() {
        action.fluentWait(getDriver(), signupLoginNav, 5);
        return action.isDisplayed(getDriver(), signupLoginNav);
    }

    public ContactUsPage clickContactUsNav() {
        action.click(getDriver(), contactUsNav);
        return new ContactUsPage();
    }

    public TestCasePage clickTestCaseNav() {
        action.click(getDriver(), testcaseNav);
        return new TestCasePage();
    }

    public AllProductPage clickProductsNav() {
        action.click(getDriver(), productsNav);
        return new AllProductPage();
    }

    public CartPage clickCartNav() {
        action.click(getDriver(), cartNav);
        return new CartPage();
    }

    public void inputSubField(String subEmail) {
        action.scrollByVisibilityOfElement(getDriver(), subscriptField);
        action.type(subscriptField, subEmail);
    }

    public void clickSubBtn() {
        action.click(getDriver(), subscriptBtn);
    }

    public boolean verifySuccessSub() {
        action.fluentWait(getDriver(), successSubMeg, 3);
        return action.isDisplayed(getDriver(), successSubMeg);
    }





}
