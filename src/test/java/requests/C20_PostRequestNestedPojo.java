package requests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C20_PostRequestNestedPojo  extends BookerBaseUrl {
     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
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
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 2243,
                                           "booking":{
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
                                            }
*/
    @Test
    public void test() {
        // set the url
        spec.pathParam("first", "booking");

        //set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jane", "Doe",
                111, true, bookingDates,
                "Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response =
                given(spec)
                        .body(expectedData)
                        .post("{first}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo(expectedData.getFirstname())
                        , "booking.lastname", equalTo(expectedData.getLastname())
                        , "booking.totalprice", equalTo(expectedData.getTotalprice())
                        , "booking.depositpaid", equalTo(expectedData.isDepositpaid())
                        , "booking.bookingdates.checkin", equalTo(bookingDates.getCheckin())
                        , "booking.bookingdates.checkout", equalTo(bookingDates.getCheckout())
                        , "booking.additionalneeds", equalTo(expectedData.getAdditionalneeds())
                );
    }
}
