package ui;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    private WebDriverWait wait;

    private static final By loggedUserLocator = By.xpath("//div[@class='userpanel-header']");
    private static final By logoutButtonLocator = By.xpath("//a[contains(@href, '/logout') and contains(@class, 'button-logout')]\n");


    public HomePage (WebDriver driver){
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getLoggedUser() {
        return getText(loggedUserLocator);
    }

    public String getTitle() {
        return "";
    }


    public void logout() {
        click(By.id("userpanel"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButtonLocator));
        click(logoutButtonLocator);
    }
}
