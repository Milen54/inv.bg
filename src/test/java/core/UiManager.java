package core;

import org.openqa.selenium.WebDriver;
import ui.HomePage;
import ui.LoginPage;

public class UiManager {

    private final WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;


    public UiManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }
}
