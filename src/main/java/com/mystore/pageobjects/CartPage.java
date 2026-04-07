package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CartPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//h2[contains(text(),'Subscription')]")
    WebElement subscriptionTitle;
    @FindBy(id = "susbscribe_email")
    WebElement subscriptField;
    @FindBy(id = "subscribe")
    WebElement subscriptBtn;
    @FindBy(id = "success-subscribe")
    WebElement successSubMeg;
    @FindBy(id = "cart_items")
    WebElement verifyCart;
    @FindBy(xpath = "//b[contains(text(),'Cart is empty!')]")
    WebElement cartEmptyMsg;

    //Cart Table
    @FindBy(xpath = "//table[@id='cart_info_table']//tbody//tr")
    List<WebElement> cartProducts;
    @FindBy(xpath = "//td[@class='cart_price']/p")
    List<WebElement> unitPrices;
    @FindBy(xpath = "//td[@class='cart_quantity']/button")
    List<WebElement> cartQuantities;
    @FindBy(xpath = "//td[@class='cart_total']/p")
    List<WebElement> totalPrices;
    @FindBy(className = "cart_quantity_delete")
    WebElement removeBtn;


    @FindBy(xpath = "//*[@id=\"do_action\"]/div[1]//a")
    WebElement CheckoutBtn;
    @FindBy(xpath = "//*[@id=\"checkoutModal\"]//a/u")
    WebElement RegisterBtn;
    @FindBy(xpath = "//h4[contains(text(),'Checkout')]")
    WebElement checkOutMsg;

    public CartPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean verifyCartPage() {
        action.fluentWait(getDriver(), verifyCart, 5);
        return action.isDisplayed(getDriver(), verifyCart);
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

    public boolean verifyNumberOfProducts(int expectedCount) {
        return cartProducts.size() >= expectedCount;
    }

    public boolean verifySubTitle() {
        return action.isDisplayed(getDriver(), subscriptionTitle);
    }

    public double parsePrice(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9]", ""));
    }

    public void verifyProductPriceQuantityTotal() {
        for (int i = 0; i < unitPrices.size(); i++) {

            double price = parsePrice(unitPrices.get(i).getText());
            int quantity = Integer.parseInt(cartQuantities.get(i).getText());
            double total = parsePrice(totalPrices.get(i).getText());

            double expectedTotal = price * quantity;
            System.out.println("Price: " + price +
                    " | Qty: " + quantity +
                    " | Expected: " + expectedTotal +
                    " | Actual: " + total);
            Assert.assertEquals(total, expectedTotal,
                    "Mismatch at product index " + i);
        }
    }

    public void verifyProductQuantity(int index, int expectedQty) {
        int actualQty = Integer.parseInt(cartQuantities.get(index).getText().trim());
        Assert.assertEquals(actualQty, expectedQty,
                "Quantity mismatch at product index " + index);
    }

    public void clickCheckOutBtn() {
        action.click(getDriver(), CheckoutBtn);
    }

    public boolean verifyCheckOutMgs() {
        action.fluentWait(getDriver(), checkOutMsg, 5);
        return action.isDisplayed(getDriver(), checkOutMsg);
    }

    public LoginSignUpPage clickRegisterBtnOnCheckOutMgs() {
        action.JSClick(getDriver(), RegisterBtn);
        return new LoginSignUpPage();
    }

    public void clickRemoveProductBtn() {
        action.click(getDriver(), removeBtn);
    }

    public boolean verifyCartEmptyMSg() {
        action.fluentWait(getDriver(), cartEmptyMsg, 10);
        return action.isDisplayed(getDriver(), cartEmptyMsg);
    }

}
