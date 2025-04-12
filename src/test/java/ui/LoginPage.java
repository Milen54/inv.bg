package ui;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static String LOGIN_PAGE = "/login";
    private static final By emailFieldLocator = By.id("loginusername");
    private static final By passwordFieldLocator = By.id("loginpassword");
    private static final By loginButtonLocator = By.id("loginsubmit");
    private static final By loginError = By.id("error");

    private static final By successfulLogout = By.id("okmsg");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getTitle();
    }

    public void navigateTo(){
        navigate(LOGIN_PAGE);
    }

    public void enterEmail(String email){
        typeText(emailFieldLocator, email);
    }

    public void enterPassword(String password){
        typeText(passwordFieldLocator, password);
    }

    public void clickLoginButton(){
        click(loginButtonLocator);
    }

    public void login (String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public String getLoginError() {
        return getText(loginError);
    }

    public String getLogoutSuccess() {
        return getText(successfulLogout);
    }
}
