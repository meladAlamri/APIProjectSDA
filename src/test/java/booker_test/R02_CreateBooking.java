package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class R02_CreateBooking extends BookerBaseUrl {
    /**
     * Given
     * *   https://restful-booker.herokuapp.com/booking
     * And
     * * {
     * "firstname" : "Jim",
     * "lastname" : "Brown",
     * "totalprice" : 111,
     * "depositpaid" : true,
     * "bookingdates" : {
     * "checkin" : "2018-01-01",
     * "checkout" : "2019-01-01"
     * },
     * "additionalneeds" : "Breakfast"
     * }
     * When
     * * send post request
     * Then
     * * status code should be 200
     * And
     * * Response body should be like:
     * {
     * "bookingid": 1,
     * "booking": {
     * "firstname": "Jim",
     * "lastname": "Brown",
     * "totalprice": 111,
     * "depositpaid": true,
     * "bookingdates": {
     * "checkin": "2018-01-01",
     * "checkout": "2019-01-01"
     * },
     * "additionalneeds": "Breakfast"
     * }
     * }
     */
    public static int bookingId;

    @Test
    public void createBookingTest() {
        // set the url
        spec.pathParam("first", "booking");

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
        Response response = given(spec).body(exactedData).post("{first}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname", equalTo(exactedData.getFirstname()),
                        "booking.lastname", equalTo(exactedData.getLastname()),
                        "booking.totalprice", equalTo(exactedData.getTotalprice()),
                        "booking.depositpaid", equalTo(exactedData.isDepositpaid()),
                        "booking.bookingdates.checkin", equalTo(exactedData.getBookingdates().getCheckin()),
                        "booking.bookingdates.checkout", equalTo(exactedData.getBookingdates().getCheckout()),
                        "booking.additionalneeds", equalTo(exactedData.getAdditionalneeds())
                );

        bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("bookingId = " + bookingId);


    }
}
