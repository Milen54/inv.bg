package api;

import api.dto.Credential;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.when;

public class LoginAPI {
    private static final String ENDPOINT = "/login/token"; // RESOURCE PATH
    private static final String BASE_URI = "https://api.inv.bg";
    private static final String BASE_PATH = "v3";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Sends POST request to /login/token
     * @param email email
     * @param password password
     * @param domain company name
     * @return Response
     */

    public Response login (String email, String password, String domain){
        Credential credential = new Credential(email, password, domain);
        return RestAssured.given()
                .log().all()
                .baseUri(BASE_URI) // This sets the base uri = https://api.inv.bg
                .basePath(BASE_PATH) // Sets the base path = v3
                .contentType(ContentType.JSON) // This will set header for the content type = application/json
                .accept(ContentType.JSON)// This will set header for the accept = application/json
                .header("User-Agent", "Mozilla")
                .body(GSON.toJson(credential)) // Sets the request body
                .when()
                .post(ENDPOINT) // Sets the HTTP verb to POST
                .prettyPeek();  // Prints the response

    }

    /**
     * Retrieves valid bearer token
     * @param email email
     * @param password password
     * @param domain company name
     * @return token as String
     */

    public String obtainToken(String email, String password, String domain) {
        Response response = login(email, password, domain);
        return response.jsonPath().getString("token");

    }

}
