package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.base.BaseUiTest;


import static core.Config.EMAIL;
import static core.Config.PASSWORD;

public class LoginPageTest extends BaseUiTest {

    @Test
    @Tag("ui")
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {
        ui.getLoginPage().navigateTo();
        Assertions.assertEquals("Вход - Milen-eood", ui.getLoginPage().getTitle());
        ui.getLoginPage().login(EMAIL, PASSWORD);

        Assertions.assertEquals(EMAIL, ui.getHomePage().getLoggedUser());
    }

    @Test
    @Tag("ui")
    @DisplayName("Cant login with invalid password")
    public void cantLoginWithInvalidPassword() {
        ui.getLoginPage().navigateTo();
        Assertions.assertEquals("Вход - Milen-eood", ui.getLoginPage().getTitle());

        ui.getLoginPage().enterEmail(EMAIL);
        ui.getLoginPage().enterPassword("bad-pasword");
        ui.getLoginPage().clickLoginButton();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("error")));
        ui.getLoginPage().getLoginError();

        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", ui.getLoginPage().getLoginError());
    }

    @Test
    @Tag("ui")
    @DisplayName("Cant login with blank credentials")
    public void cantLoginWithBlankCredentials() {
        ui.getLoginPage().navigateTo();
        Assertions.assertEquals("Вход - Milen-eood", ui.getLoginPage().getTitle());

        ui.getLoginPage().clickLoginButton();
        ui.getLoginPage().getLoginError();
        Assertions.assertEquals("Моля, попълнете вашия email", ui.getLoginPage().getLoginError());
    }

    @Test
    @Tag("ui")
    @DisplayName("Can logout")
    public void canLogout() {
        ui.getLoginPage().navigateTo();
        Assertions.assertEquals("Вход - Milen-eood", ui.getLoginPage().getTitle());

        ui.getLoginPage().enterEmail(EMAIL);
        ui.getLoginPage().enterPassword(PASSWORD);
        ui.getLoginPage().clickLoginButton();

        Assertions.assertEquals(EMAIL, ui.getHomePage().getLoggedUser());

        ui.getHomePage().logout();

        Assertions.assertEquals("Вие излязохте от акаунта си.", ui.getLoginPage().getLogoutSuccess());
        Assertions.assertEquals("Вход - Milen-eood", ui.getLoginPage().getTitle());
    }
}
