package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseClass {

    Action action = new Action();

    //Menu Item
    @FindBy(xpath = "//a[contains(text(),'Logged in as')]/b")
    WebElement username;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement logoutNav;
    @FindBy(xpath = "//a[contains(text(),'Delete Account')]")
    WebElement deleteAccountNav;
    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    WebElement cartNav;

    //Product
    @FindBy(css = "a[href*='product_details']")
    WebElement viewProductBtn;

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean verifyLogoutNav() {
        return action.isDisplayed(getDriver(),logoutNav);
    }

    public boolean verifyDeleteAccountNav() {
        return action.isDisplayed(getDriver(),deleteAccountNav);
    }

    public AccountDeletedPage clickDeleteAccountBtn() {
        action.click(getDriver(), deleteAccountNav);
        return new AccountDeletedPage();
    }

    public boolean verifyUsernameLogged() {
        action.fluentWait(getDriver(), username, 10);
        return action.isDisplayed(getDriver(), username);
    }

    public IndexPage clickLogoutBtn() {
        action.JSClick(getDriver(), logoutNav);
        return new IndexPage();
    }

    public String getCurrURL() {
        String homePageURL = getDriver().getCurrentUrl();
        return homePageURL;
    }

    public ProductDetailPage clickViewProduct() {
        action.scrollByVisibilityOfElement(getDriver(), viewProductBtn);
        action.click(getDriver(), viewProductBtn);
        return new ProductDetailPage();
    }

    public CartPage clickCartNav() {
        action.click(getDriver(), cartNav);
        return new CartPage();
    }

}
