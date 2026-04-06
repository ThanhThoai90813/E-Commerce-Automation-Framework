package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//div[@class='product-information']/h2")
    WebElement productName;
    @FindBy(xpath = "//div[@class='product-information']/p[contains(text(),'Category')]")
    WebElement category;
    @FindBy(xpath = "//div[@class='product-information']//span/span")
    WebElement price;
    @FindBy(xpath = "//div[@class='product-information']/p[b[text()='Availability:']]")
    WebElement availability;
    @FindBy(xpath = "//div[@class='product-information']/p[b[text()='Condition:']]")
    WebElement condition;
    @FindBy(xpath = "//div[@class='product-information']/p[b[text()='Brand:']]")
    WebElement brand;
    @FindBy(xpath = "//div[@class='view-product']//img")
    WebElement productImage;

    //Add To Cart Flow
    @FindBy(id = "quantity")
    WebElement quantity;
    @FindBy(xpath = "//*[@id=\"cartModal\"]//h4")
    WebElement addToCartMessage;
    @FindBy(xpath = "//*[@id=\"cartModal\"]/div//p[2]/a/u")
    WebElement ViewCartText;
    @FindBy(xpath = "/html/body/section//span/button")
    WebElement AddToCartBtn;

    public ProductDetailPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean verifyProductName() {
        action.fluentWait(getDriver(), productName, 10);
        return action.isDisplayed(getDriver(), productName);
    }

    public boolean verifyProductCategory() {
        return action.isDisplayed(getDriver(), category);
    }

    public boolean verifyProductPrice() {
        return action.isDisplayed(getDriver(), price);
    }

    public boolean verifyProductAvailability() {
        return action.isDisplayed(getDriver(), availability);
    }

    public boolean verifyProductCondition() {
        return action.isDisplayed(getDriver(), condition);
    }

    public boolean verifyProductBrand() {
        return action.isDisplayed(getDriver(), brand);
    }

    public boolean verifyProductImg() {
        return action.isDisplayed(getDriver(), productImage);
    }

    public void enterQuantity(String Quantity) {
        action.type(quantity, Quantity);
    }

    public void clickOnAddToCart() {
        action.click(getDriver() ,AddToCartBtn);
    }

    public boolean validateAddToCart() {
        action.fluentWait(getDriver(), addToCartMessage, 5);
        return action.isDisplayed(getDriver(), addToCartMessage);
    }

    public CartPage clickViewCartText() {
        action.JSClick(getDriver(), ViewCartText);
        return new CartPage();
    }

}
