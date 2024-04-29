package requests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C25_ObjectMapperUtilsGetRequest extends BookerBaseUrl {
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
                "totalprice": 102,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Extra pillows please"
            }
*/

    @Test
    public void objectMapperUtilsGetRequestTest() {
        // set the url
        spec.pathParams("first", "booking", "second", "466");

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
                        .get("{first}/{second}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .statusCode(200)
                .body("firstname", equalTo(expectedData.getFirstname())
                        , "lastname", equalTo(expectedData.getLastname())
                        , "totalprice", equalTo(expectedData.getTotalprice())
                        , "depositpaid", equalTo(expectedData.isDepositpaid())
                        , "bookingdates.checkin", equalTo(expectedData.getBookingdates().getCheckin())
                        , "bookingdates.checkout", equalTo(expectedData.getBookingdates().getCheckout())
                        , "additionalneeds", equalTo(expectedData.getAdditionalneeds())
                );
    }
}
