package Requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import utils.SetProperties;

import static io.restassured.RestAssured.given;

public class DeleteRequests extends SetProperties {
    private String ListPath ="list/";
    PostRequests postRequests = new PostRequests();
    public String deleteList(){
        String listId = postRequests.createList();
        String apiKey = getApi_key();
        String session = postRequests.createSession();
        String url = getUrl_host();

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",apiKey)
                .queryParam("session_id", session)
                .when()
                .delete(url+ListPath +listId)
                .then()
                .statusCode(404)
                .log()
                .body()
                .extract()
                .response();
        Assert.assertEquals("false",response.jsonPath().getString("success"));
        Assert.assertEquals("The resource you requested could not be found.",response.jsonPath().getString("status_message"));
        return response.jsonPath().getString("status_code");
    }

}
