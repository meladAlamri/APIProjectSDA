package Homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class H03 {
     /**
      * Given <a href="https://reqres.in/api/users/3">reqres.in</a><br>
      * When User sends a GET Request to the url<br>
      * Then HTTP Status Code should be 200<br>
      * And Content Type should be JSON<br>
      * And Status Line should be HTTP/1.1 200 OK
      */

    @Test
    public void test() {
//        1. Set the URl
        String url = "https://reqres.in/api/users/3";

//        2. Set the expected data
//        3. Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

//        4. Do Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK");

    }
}
