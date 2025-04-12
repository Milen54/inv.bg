package api;

import api.dto.Item;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static core.Config.BASE_PATH;
import static core.Config.BASE_URI;
import static io.restassured.path.json.config.JsonParserType.GSON;

public class ItemAPI {

    private static final String ENDPOINT = "/items";
    private String token;

    public ItemAPI(String token) {
        this.token = token;
    }

    public Response getItems(){
        return RestAssured.given()
                .log().all()  //Log everything in (uri, headers, body etc) the console
                .baseUri(BASE_URI) // This sets the base uri = https://api.inv.bg
                .auth().oauth2(token)
                .basePath(BASE_PATH) // Sets the base path = v3
                .contentType(ContentType.JSON) // This will set header for the content type = application/json
                .accept(ContentType.JSON)// This will set header for the accept = application/json
                .header("User-Agent", "Mozilla")  // Sets user-agent header to prevent blocking from the server
                .when()
                .get(ENDPOINT) // Sets the HTTP verb to GET
                .prettyPeek();  // Prints the response
    }

    //TODO: Implement method and create one test
    //GET Request
    public Response getItem(int id){
        return RestAssured.given()
                .log().all()
                .baseUri(BASE_URI)
                .auth().oauth2(token)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .when()
                .get(ENDPOINT + "/" + id)
                .prettyPeek();
    }

    //TODO: Implement method and create one test
    //POST request
    public Response createItem(Item item){
        return RestAssured.given()
                .log().all()
                .baseUri(BASE_URI)
                .auth().oauth2(token)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .body(item)
                .when()
                .post(ENDPOINT)
                .prettyPeek();
    }

    public Response deleteItem(int id) {
        return RestAssured
                .given()
                .baseUri(BASE_URI)
                .auth().oauth2(token)
                .basePath(BASE_PATH)
                .when()
                .delete(ENDPOINT + "/" + id);
    }

}
