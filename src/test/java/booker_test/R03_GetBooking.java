package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class R03_GetBooking extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        Send GET request to the url
    Then
        Assert that status code is 200
    And
        Response body should be like:
     {
         "bookingid": 1,
            "booking": {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                 "depositpaid": true,
                   "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                        },
            "additionalneeds": "Breakfast"
     *          }
     */

    @Test
    public void getBookingIdTest() {
        // set the url
        spec.pathParams("first", "booking", "second", R02_CreateBooking.bookingId);


        //Set the exacted data
        String strJson = """
                {
                            "firstname" : "Jim",
                            "lastname" : "Brown",
                            "totalprice" : 111,
                            "depositpaid" : true,
                            "bookingdates" : {
                                "checkin" : "2018-01-01",
                                "checkout" : "2019-01-01"
                            },
                            "additionalneeds" : "Breakfast"
                        }""";
        BookingPojo exactedData = ObjectMapperUtils.convertJsonToPojo(strJson, BookingPojo.class);
        System.out.println("exactedData = " + exactedData);

        //send the request and get the response
        Response response = given(spec).get("{first}/{second}");
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
