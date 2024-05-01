package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class R07_HealthCheck extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/ping
    When
        Send get request to the url
    Then
        Assert that status code is 201
    And
        Assert that status line  is HTTP/1.1 201 Created:
    And
        Response body should be: "Created"
     */


    @Test
    public void healthCheckBookingTest() {
        // set the url
        spec.pathParam("first", "ping");


        //Set the exacted data
        String exactedData = "Created";

        //send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(201)
                .statusLine("HTTP/1.1 201 Created");

        Assert.assertEquals(response.asString(), exactedData);

    }
}
