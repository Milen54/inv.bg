package api;

import api.base.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("login")

public class LoginAPITest extends BaseApiTest {

    @Test
    @Tag("api")
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {
        Response response = api.getLoginAPI().login("denkovmilen@abv.bg", "parola123", "milen-ood"); // Send POST request
        Assertions.assertEquals(200, response.statusCode());  // Checks response status code
        String token = response.jsonPath().getString("token");  // Extracts bearer token from the response
        Assertions.assertFalse(token.isBlank(), "The token is blank"); // Checks that bearer token is not blank
        String expString = response.jsonPath().getString("expires_string");
        Assertions.assertFalse(expString.isBlank(), "The expires string is blank");

    }

    @Test
    @Tag("api")
    @DisplayName("Cant login with invalid credentials")
    public void cantLoginWithInvalidCredentials() {
        Response response = api.getLoginAPI().login("denkovmilen@abv.bg", "bad-password", "milen-ood"); // Send POST request
        Assertions.assertEquals(401, response.statusCode());  // Checks response status code
        String error = response.jsonPath().getString("error");
        Assertions.assertEquals("Wrong username or password",  error);

    }

    @Test
    @Tag("api")
    @DisplayName("Cant login with invalid company")
    public void cantLoginWithInvalidCompany() {
        Response response = api.getLoginAPI().login("denkovmilen@abv.bg", "parola123", "wrong-company"); // Send POST request
        Assertions.assertEquals(401, response.statusCode());  // Checks response status code
        String error = response.jsonPath().getString("error");
        Assertions.assertEquals("Firm not found",  error);

    }

    @Test
    @Tag("api")
    @DisplayName("Cant login with blank credentials")
    public void cantLoginWithBlankCredentials() {
        Response response = api.getLoginAPI().login(null, null, null); // Send POST request
        Assertions.assertEquals(400, response.statusCode());  // Checks response status code
        String error = response.jsonPath().getString("error");
        Assertions.assertEquals("Липсва тяло на заявката",  error);

    }
}
