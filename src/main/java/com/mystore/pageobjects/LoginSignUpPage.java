package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSignUpPage extends BaseClass {

    Action action = new Action();

    //SignUp
    @FindBy(xpath = "//h2[contains(text(), 'New User Signup!')]")
    WebElement verifySignUpTitle;
    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement signUpNameField;
    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement signUpEmailField;
    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement signUpBtn;
    @FindBy(xpath = "//p[contains(text(), 'Email Address already exist!')]")
    WebElement incorrectSignUpMsg;

    //Login
    @FindBy(xpath = "//h2[contains(text(), 'Login to your account')]")
    WebElement verifyLoginTitle;
    @FindBy(name = "email")
    WebElement loginEmail;
    @FindBy(name = "password")
    WebElement loginPassword;
    @FindBy(xpath = "//button[@data-qa='login-button']")
    WebElement loginButton;
    @FindBy(xpath = "//p[contains(text(), 'Your email or password is incorrect!')]")
    WebElement incorrectLoginMsg;

    public LoginSignUpPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean verifySignUpTitle() {
        return action.isDisplayed(getDriver(), verifySignUpTitle);
    }

    public boolean verifyLoginTitle() {
        return action.isDisplayed(getDriver(), verifyLoginTitle);
    }

    public boolean incorrectLoginMsgCheck() {
        return action.isDisplayed(getDriver(), incorrectLoginMsg);
    }

    public boolean incorrectSignUpMsgCheck() {
        return action.isDisplayed(getDriver(), incorrectSignUpMsg);
    }

    public HomePage Login(String eml, String pw){
        action.type(loginEmail, eml);
        action.type(loginPassword, pw);
        action.click(getDriver(), loginButton);
        return new HomePage();
    }

    public AccountCreationPage Signup(String newName, String newEmail) {
        action.type(signUpNameField, newName);
        action.type(signUpEmailField, newEmail);
        action.click(getDriver(), signUpBtn);

        return new AccountCreationPage();
    }


}
