package Homework.H13;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class H13_Delete extends setUp{
    //Write an automation test that will create a 'user' then read,
    // update and delete the created user using the "https://petstore.swagger.io/" document.
    // (Create a classes for each request.)


    @Test()
    public void deleteUserTest() {

        Response response = given()
                .body(Data)
                .contentType(ContentType.JSON)
                .delete(url + "/" + Data.getUsername());
        response.prettyPrint();

        //do assertion
        response
                .then()
                .assertThat()
                .body("code", equalTo(response.getStatusCode())
                        , "type", equalTo(type)
                        , "message", equalTo(Data.getUsername())
                );
    }


}
