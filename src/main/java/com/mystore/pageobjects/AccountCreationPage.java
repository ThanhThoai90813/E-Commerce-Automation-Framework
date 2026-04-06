package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreationPage extends BaseClass {

    Action action = new Action();

    //Title
    @FindBy(xpath = "//b[contains(text(), 'Enter Account Information')]")
    WebElement accountCreationPageTitle;

    //Account Information
    @FindBy(id = "uniform-id_gender1")
    WebElement genDer1;
    @FindBy(id = "uniform-id_gender2")
    WebElement genDer2;
    @FindBy(xpath = "//*[@id=\"password\"]")
    WebElement passwordField;
    @FindBy(id = "days")
    WebElement daysField;
    @FindBy(id = "months")
    WebElement monthsField;
    @FindBy(id = "years")
    WebElement yearsField;
    @FindBy(id = "newsletter")
    WebElement newsletterCheckbox;
    @FindBy(id = "optin")
    WebElement offersCheckbox;

    //Address Information
    @FindBy(id = "first_name")
    WebElement firstNameField;
    @FindBy(id = "last_name")
    WebElement lastNameField;
    @FindBy(id = "company")
    WebElement companyField;
    @FindBy(id = "address1")
    WebElement address1Field;
    @FindBy(id = "address2")
    WebElement address2Field;
    @FindBy(id = "country")
    WebElement countryDropdown;
    @FindBy(id = "state")
    WebElement stateField;
    @FindBy(id = "city")
    WebElement cityField;
    @FindBy(id = "zipcode")
    WebElement zipcodeField;
    @FindBy(id = "mobile_number")
    WebElement mobileNumberField;

    //Confirm Button
    @FindBy(xpath = "//button[@data-qa='create-account']")
    WebElement createAccountBtn;

    public AccountCreationPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean verifyAccountCreationPage() {
        action.fluentWait(getDriver(), accountCreationPageTitle, 10);
        return action.isDisplayed(getDriver(), accountCreationPageTitle);
    }

    //Account Information//

    //Radio
    public void selectGender(String gender) {
        if (gender == null) return;

        if (gender.equalsIgnoreCase("Mr.") || gender.equalsIgnoreCase("Male")) {
            action.click(getDriver(), genDer1);
        } else if (gender.equalsIgnoreCase("Mrs.") || gender.equalsIgnoreCase("Female")) {
            action.click(getDriver(), genDer2);
        }
    }

    public void inputPassword(String pws) {
        action.type(passwordField, pws);
    }

    public void selectDay(String day) {
        action.selectByVisibleText(day, daysField);
    }

    public void selectMonth(String month) {
        action.selectByVisibleText(month, monthsField);
    }

    public void selectYear(String year) {
        action.selectByVisibleText(year, yearsField);
    }

    //Checkbox
    public void setNewsletter(String value) {
        if ("X".equalsIgnoreCase(value) && !newsletterCheckbox.isSelected()) {
            action.click(getDriver(), newsletterCheckbox);
        }
    }

    public void setSpecialOffers(String value) {
        if ("X".equalsIgnoreCase(value) && !offersCheckbox.isSelected()) {
            action.click(getDriver(), offersCheckbox);
        }
    }

    //Address Information//
    public void inputFirstname(String Fname) {
        action.type(firstNameField, Fname);
    }

    public void inputLastname(String Lname) {
        action.type(lastNameField, Lname);
    }

    public void inputCompany(String company) {
        action.type(companyField, company );
    }

    public void inputAddress1(String adress1) {
        action.type(address1Field, adress1);
    }

    public void inputAddress2(String adress2) {
        action.type(address2Field, adress2);
    }

    public void selectCountry(String country) {
        action.selectByVisibleText(country, countryDropdown);
    }

    public void inputState(String state) {
        action.type(stateField, state);
    }

    public void inputCity(String city) {
        action.type(cityField, city);
    }

    public void inputZipcode(String zipcode) {
        action.type(zipcodeField, zipcode);
    }

    public void inputPhoneNumber(String phone) {
        action.type(mobileNumberField, phone);
    }

    //Confirm
    public AccountCreatedPage clickCreateAccountBtn() {
        action.JSClick(getDriver(), createAccountBtn);
        return new AccountCreatedPage();
    }

}
