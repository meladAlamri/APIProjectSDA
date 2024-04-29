package requests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C26_ObjectMapperUtilsPostRequest extends BookerBaseUrl {
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
    public void objectMapperUtilsGetRequestTest() {
        // set the url
        spec.pathParam("first", "booking");

        //set the expected data
        String strJson = """
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
                            }""";
        BookingPojo expectedData = ObjectMapperUtils.convertJsonToPojo(strJson, BookingPojo.class);

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
                        , "booking.bookingdates.checkin", equalTo(expectedData.getBookingdates().getCheckin())
                        , "booking.bookingdates.checkout", equalTo(expectedData.getBookingdates().getCheckout())
                        , "booking.additionalneeds", equalTo(expectedData.getAdditionalneeds())
                );
    }
}
