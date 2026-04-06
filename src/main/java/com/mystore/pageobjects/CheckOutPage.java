package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CheckOutPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//*[@id=\"do_action\"]/div[1]//a")
    WebElement CheckoutBtn;

    @FindBy(xpath = "//td[@class='cart_price']/p")
    List<WebElement> unitPrices;

    @FindBy(xpath = "//td[@class='cart_quantity']/button")
    List<WebElement> cartQuantity;

    @FindBy(xpath = "//tr[not(@id)]//p[@class='cart_total_price']")
    WebElement totalAmount;

    @FindBy(xpath = "//*[@id=\"ordermsg\"]/textarea")
    WebElement orderMsgField;

    @FindBy(xpath = "//a[contains(@class,'check_out')]")
    WebElement placeOrderBtn;

    public CheckOutPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void ClickCheckOutBtn() {
        action.click(getDriver(), CheckoutBtn);
    }

    // parse giá (dùng chung)
    public double parsePrice(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9]", ""));
    }

    // tính tổng từ từng sản phẩm
    public double calculateTotalFromItems() {
        double sum = 0;

        for (int i = 0; i < unitPrices.size(); i++) {
            double price = parsePrice(unitPrices.get(i).getText());
            int qty = Integer.parseInt(cartQuantity.get(i).getText());

            sum += price * qty;
        }

        return sum;
    }

    // lấy total hiển thị
    public double getDisplayedTotal() {
        return parsePrice(totalAmount.getText());
    }

    // verify total
    public void verifyTotal() {
        double expected = calculateTotalFromItems();
        double actual = getDisplayedTotal();
        Assert.assertEquals(actual, expected, "Total price mismatch!");
    }

    public void inputOrderMsg(String ordermsg) {
        action.type(orderMsgField, ordermsg);
    }

    public PaymentPage clickPlaceOrders() {
        action.click(getDriver(), placeOrderBtn);
        return new PaymentPage();
    }

}
