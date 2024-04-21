package Homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class H10 {
    /**
     * Using the https://petstore.swagger.io/ document,
     * write an automation test that finds the number of "pets" with the status "available"
     * and asserts that there are more than 100.
     */

    @Test
    public void test() {
        //Set the url
        String url = "https://petstore.swagger.io/v2/pet/findByStatus?status=available";

        //set the request and got the response
        Response response = given().get(url);
        response.prettyPrint();

        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("", hasSize(greaterThan(100)))
        ;

    }
}
