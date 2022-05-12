package Requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import utils.SetProperties;

import static io.restassured.RestAssured.given;

public class PostRequests extends SetProperties {
    private String login_path="authentication/token/validate_with_login";
    private String creatgesession_path="authentication/session/new";
    private String createListPath="list";

    private String addMovieListPath ="/list/";

    private String addMovieItemPath ="/add_item";

    private String removeMovie = "/remove_item";
    GetRequests getRequests = new GetRequests();
    JSONObject jsonObject = new JSONObject();


    public String getLoginWithLogin(){
        jsonObject
                .put("username", getUsername())
                .put("password", getPassword())
                .put("request_token",getRequests.getToken());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+ login_path)
                .then()
                .statusCode(200)
                //.log()
                //.body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }


    public String createSession(){
        jsonObject
                .put("request_token",getLoginWithLogin());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+ creatgesession_path)
                .then()
                .statusCode(200)
                //.log()
                //.body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("session_id");
    }

    public String createList(){
        String name="TestAUT List";
        String description="TestAUT List Description";
        String language="en";
        jsonObject
                .put("name",name)
                .put("description",description)
                .put("language",language);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+createListPath)
                .then()
                .statusCode(201)
                .log()
                .body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was created successfully.",response.jsonPath().getString("status_message"));
        return response.jsonPath().getString("list_id");
    }

    public String addMovie(){
        jsonObject
                .put("media_id", getMedia_id());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+addMovieListPath +createList() +addMovieItemPath)
                .then()
                .statusCode(201)
                .log()
                .body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was updated successfully.",response.jsonPath().getString("status_message"));
        return response.jsonPath().getString("status_code");
    }

    public String removeMovie(){
        jsonObject
                .put("media_id", getMedia_id());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+addMovieListPath +createList() + removeMovie)
                .then()
                .statusCode(201)
                .log()
                .body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was updated successfully.",response.jsonPath().getString("status_message"));
        return response.jsonPath().getString("status_code");
    }


}
