package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class R01_GetBookingIds extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        Send GET request to the url
    Then
        Assert that status code is 200
    And
        Response body should have more than 0 elements
     */

    @Test
    public void getBookingIdsTest() {
        // set the url
        spec.pathParam("first", "booking");

        //Set the exacted data
        //we do not have

        //send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)));

    }
}
