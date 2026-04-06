package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllProductPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//h2[contains(text(),'All Products')]")
    WebElement productPageTitle;

    @FindBy(css = ".features_items .col-sm-4")
    WebElement productDisplay;

    @FindBy(css = "a[href*='product_details']")
    WebElement viewProductBtn;

    @FindBy(id = "search_product")
    WebElement searchProduct;

    @FindBy(id="submit_search")
    WebElement submitSearchBtn;

    @FindBy(xpath = "//h2[contains(text(),'Searched Products')]")
    WebElement searchProductTitle;

    @FindBy(xpath = "(//div[@class='product-image-wrapper'])[1]//div[contains(@class,'productinfo')]//a[contains(@class,'add-to-cart')]")
    WebElement product1NormalAdd;

    @FindBy(xpath = "(//div[@class='product-image-wrapper'])[2]")
    WebElement product2Card;

    // button trong overlay
    @FindBy(xpath = "(//div[@class='product-image-wrapper'])[2]//div[@class='product-overlay']//a[contains(@class,'add-to-cart')]")
    WebElement product2OverlayAdd;

    @FindBy(xpath = "//button[text()='Continue Shopping']")
    WebElement continueShoppingBtn;

    @FindBy(xpath = "//u[text()='View Cart']")
    WebElement viewCartBtn;

    @FindBy(xpath = "//h4[contains(text(),'Added!')]")
    WebElement addedMsg;

    public AllProductPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean isProductAvailable() {
        return action.isDisplayed(getDriver(), productDisplay);
    }

    public boolean verifyProductPage() {
        action.fluentWait(getDriver(), productPageTitle, 5);
        return action.isDisplayed(getDriver(), productPageTitle);
    }

    public ProductDetailPage clickViewFirstProduct() {
        action.click(getDriver(), viewProductBtn);
        return new ProductDetailPage();
    }

    public void InputSearchKey(String text) {
        action.type(searchProduct, text);
    }

    public void clickSearchBtn() {
        action.click(getDriver(), submitSearchBtn);
    }

    public boolean verifySearchProduct() {
        return action.isDisplayed(getDriver(), searchProductTitle);
    }

    public void clickNormalAddToCartProduct1() {
        action.scrollByVisibilityOfElement(getDriver(), product1NormalAdd);
        action.JSClick(getDriver(), product1NormalAdd);
    }

    public void clickAddToCartProduct2Overlay() {
        action.scrollByVisibilityOfElement(getDriver(), product2OverlayAdd);
        action.JSClick(getDriver(), product2OverlayAdd);
    }

    public void clickContinueShopping() {
        action.fluentWait(getDriver(), continueShoppingBtn, 5);
        action.JSClick(getDriver(), continueShoppingBtn);
    }

    public CartPage clickViewCart() {
        action.JSClick(getDriver(), viewCartBtn);
        return new CartPage();
    }

    public boolean verifyAddedCartMsg() {
        action.fluentWait(getDriver(), addedMsg, 5);
        return action.isDisplayed(getDriver(), addedMsg);
    }

}
