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

    //  billing address
    @FindBy(id = "address_invoice")
    WebElement addressInvoiceBlock;
    @FindBy(css = "#address_invoice .address_firstname")
    WebElement invoiceName;
    @FindBy(css = "#address_invoice .address_address1")
    List<WebElement> invoiceAddressLines;
    @FindBy(css = "#address_invoice .address_city")
    WebElement invoiceCity;
    @FindBy(css = "#address_invoice .address_country_name")
    WebElement invoiceCountry;
    @FindBy(css = "#address_invoice .address_phone")
    WebElement invoicePhone;

    //  delivery address
    @FindBy(id = "address_delivery")
    WebElement addressDeliveryBlock;
    @FindBy(css = "#address_delivery .address_firstname")
    WebElement deliveryName;
    @FindBy(css = "#address_delivery .address_address1")
    List<WebElement> deliveryAddressLines;
    @FindBy(css = "#address_delivery .address_city")
    WebElement deliveryCity;
    @FindBy(css = "#address_delivery .address_country_name")
    WebElement deliveryCountry;
    @FindBy(css = "#address_delivery .address_phone")
    WebElement deliveryPhone;

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

    public void verifyAddressInvoice() {
        Assert.assertTrue(addressInvoiceBlock.isDisplayed(), "Address invoice block not displayed");

        Assert.assertFalse(invoiceName.getText().trim().isEmpty(), "Name is empty");

        // check ít nhất 1 dòng address có data
        boolean hasAddress = false;
        for (WebElement addr : invoiceAddressLines) {
            if (!addr.getText().trim().isEmpty()) {
                hasAddress = true;
                break;
            }
        }
        Assert.assertTrue(hasAddress, "Address is empty");

        Assert.assertFalse(invoiceCity.getText().trim().isEmpty(), "City is empty");
        Assert.assertFalse(invoiceCountry.getText().trim().isEmpty(), "Country is empty");
        Assert.assertFalse(invoicePhone.getText().trim().isEmpty(), "Phone is empty");
    }

    public void verifyAddressDelivery() {
        Assert.assertTrue(addressDeliveryBlock.isDisplayed(), "Delivery address block not displayed");

        Assert.assertFalse(deliveryName.getText().trim().isEmpty(), "Delivery name is empty");

        boolean hasAddress = false;
        for (WebElement addr : deliveryAddressLines) {
            if (!addr.getText().trim().isEmpty()) {
                hasAddress = true;
                break;
            }
        }
        Assert.assertTrue(hasAddress, "Delivery address is empty");

        Assert.assertFalse(deliveryCity.getText().trim().isEmpty(), "Delivery city is empty");
        Assert.assertFalse(deliveryCountry.getText().trim().isEmpty(), "Delivery country is empty");
        Assert.assertFalse(deliveryPhone.getText().trim().isEmpty(), "Delivery phone is empty");
    }

    public void verifyBothAddresses() {
        action.fluentWait(getDriver(), addressInvoiceBlock, 10 );
        verifyAddressInvoice();
        verifyAddressDelivery();
    }

}
