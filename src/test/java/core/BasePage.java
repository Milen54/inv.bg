package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static core.Config.LOGIN_URL;

public class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    protected void navigate(String url){
        driver.get(LOGIN_URL + url);
    }

    protected void typeText(By locator, String text){
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    protected String getText(By locator){
        WebElement element = driver.findElement(locator);
        return element.getText().strip(); //The strip is added to remove leading and trailing spaces in the strings like nbsp
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
