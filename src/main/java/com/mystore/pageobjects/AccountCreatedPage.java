package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//b[contains(text(), 'Account Created!')]")
    WebElement confirmCreatedTitle;
    @FindBy(xpath = "//p[normalize-space()='Congratulations! Your new account has been successfully created!']")
    WebElement confirmCreatedMess;
    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement continueBtn;

    public AccountCreatedPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean validateCreatedTitle() {
        action.fluentWait(getDriver(), confirmCreatedTitle, 5);
        return action.isDisplayed(getDriver(), confirmCreatedTitle);
    }

    public boolean validateCreatedMessage() {
        action.fluentWait(getDriver(), confirmCreatedMess, 5);
        return action.isDisplayed(getDriver(), confirmCreatedMess);
    }

    public HomePage clickContinueBtn() {
        action.JSClick(getDriver(), continueBtn);
        return new HomePage();
    }
}
