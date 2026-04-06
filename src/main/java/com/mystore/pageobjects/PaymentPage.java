package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//input[@data-qa='name-on-card']")
    WebElement nameOnCardField;

    @FindBy(xpath = "//input[@data-qa='card-number']")
    WebElement cardNumberField;

    @FindBy(xpath = "//input[@data-qa='cvc']")
    WebElement cvcField;

    @FindBy(xpath = "//input[@data-qa='expiry-month']")
    WebElement expiryMonthField;

    @FindBy(xpath = "//input[@data-qa='expiry-year']")
    WebElement expiryYearField;

    @FindBy(id = "submit")
    WebElement confirmOrderBtn;

    @FindBy(xpath = "//*[@id=\"form\"]//p")
    WebElement validateOrderPlaced;

    public PaymentPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void typeToCardField(String cardfield) {
        action.type(nameOnCardField, cardfield);
    }

    public void typeToNumberField(String number) {
        action.type(cardNumberField, number);
    }

    public void typeToCvcField(String cvc) {
        action.type(cvcField, cvc);
    }

    public void typeToExpirationMothField(String expMth) {
        action.type(expiryMonthField, expMth);
    }

    public void typeToExpirationYearField(String expYear) {
        action.type(expiryYearField, expYear);
    }

    public void clickConfirmOrder() {
        action.click(getDriver(), confirmOrderBtn);
    }

    public boolean validateOrderPlaced() {
        return action.isDisplayed(getDriver(), validateOrderPlaced);
    }
}
