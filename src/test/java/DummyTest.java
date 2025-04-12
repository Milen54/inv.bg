import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DummyTest {

    private static final String LOGIN_PAGE = "https://milen-ood.inv.bg";

    @Test
    @DisplayName("Can sum numbers")
    public void canSumNumbers() {
        Assertions.assertEquals(4, 2+2);

    }

    @Test
    @DisplayName("Can load login page")
    public void canLoadLoginPAge() {
        WebDriver driver = new ChromeDriver();
        driver.get(LOGIN_PAGE);
        String title = driver.getTitle();
        Assertions.assertEquals("Вход - Milen-eood", title);
        driver.quit();
    }
 }
