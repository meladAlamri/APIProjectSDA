package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class R06_DeleteBooking extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        Send Delete request to the url
    Then
        Assert that status code is 201
    And
        Assert that status line  is HTTP/1.1 201 Created:
    And
        Response body should be: "Created"
     */


    @Test
    public void deleteBookingTest() {
        // set the url
        spec.pathParams("first", "booking", "second", R02_CreateBooking.bookingId);


        //Set the exacted data
        String exactedData = "Created";

        //send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(201)
                .statusLine("HTTP/1.1 201 Created");

        Assert.assertEquals(response.asString(),exactedData);

    }
}
