package requests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C19_GetRequestNestedPojo extends BookerBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/466
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Extra pillows please"
            }
*/

    @Test
    public void getRequestPojoTest() {
        // set the url
        //spec.pathParams("first", "booking", "second", "48");

        //set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jane", "Doe",
                111, true, bookingDates,
                "Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response =
                given(spec)
                        .get("booking/99");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .statusCode(200)
                .body("firstname", equalTo(expectedData.getFirstname())
                        , "lastname", equalTo(expectedData.getLastname())
                        , "totalprice", equalTo(expectedData.getTotalprice())
                        , "depositpaid", equalTo(expectedData.isDepositpaid())
                        , "bookingdates.checkin", equalTo(bookingDates.getCheckin())
                        , "bookingdates.checkout", equalTo(bookingDates.getCheckout())
                        , "additionalneeds", equalTo(expectedData.getAdditionalneeds())
                );
    }
}
