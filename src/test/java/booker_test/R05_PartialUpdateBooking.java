package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;

import static booker_test.R04_UpdateBooking.exactedData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class R05_PartialUpdateBooking extends BookerBaseUrl {
       /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
            "firstname" : "Mary",
            "lastname" : "Star"
        }
    When
        Send PATCH request to the url
    Then
        Status code should be 200
    And
        Response body should be like:
        {
            "firstname" : "Mary",
            "lastname" : "Star",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
     */

    @Test
    void partialUpdateBookingTest() {
        // set the url
        spec.pathParams("first", "booking", "second", R02_CreateBooking.bookingId);

        //Set the exacted data
        exactedData.setFirstname("Mary");
        exactedData.setLastname("Star");
        System.out.println("exactedData = " + exactedData);

        //send the request and get the response
        Response response = given(spec)
                .body(exactedData)
                .patch("{first}/{second}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstname", equalTo(exactedData.getFirstname()),
                        "lastname", equalTo(exactedData.getLastname()),
                        "totalprice", equalTo(exactedData.getTotalprice()),
                        "depositpaid", equalTo(exactedData.isDepositpaid()),
                        "bookingdates.checkin", equalTo(exactedData.getBookingdates().getCheckin()),
                        "bookingdates.checkout", equalTo(exactedData.getBookingdates().getCheckout()),
                        "additionalneeds", equalTo(exactedData.getAdditionalneeds())
                );



    }
}
