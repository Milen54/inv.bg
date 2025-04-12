package ui.base;

import core.UiManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseUiTest {

        protected WebDriver driver;
        protected WebDriverWait wait;
        protected UiManager ui;

        @BeforeEach
        public void setUp() {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            ui = new UiManager(driver);
        }

        @AfterEach
        public void tearDown() {
            driver.quit();
        }
}
