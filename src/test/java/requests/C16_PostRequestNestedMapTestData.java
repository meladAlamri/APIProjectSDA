package requests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_data.BookerTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class C16_PostRequestNestedMapTestData extends BookerBaseUrl {

     /*
    Given
        1) https://restful-booker.herokuapp.com/booking
        2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 15,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-03-07",
                "checkout": "2024-09-25"
            },
            "additionalneeds": "Lunch"
           }
    When
        I send POST Request to the Url
    Then
        Status code is 200
        And response body should be like {
                                            "bookingid": 2243,
                                            "booking": {
                                                "firstname": "John",
                                                "lastname": "Doe",
                                                "totalprice": 471,
                                                "depositpaid": true,
                                                "bookingdates": {
                                                    "checkin": "2023-03-07",
                                                    "checkout": "2024-09-25"
                                                },
                                                "additionalneeds": "Lunch"
                                            }
                                        }
 */

    @Test
    public void test() {
        //set the url
        spec.pathParam("first", "booking");

        //set the expected data
        //First inner json as map
        Map<String, String> bookingDatesMap = BookerTestData
                .bookingDataMethod("2023-03-07", "2024-09-25");

        //Second outer json as map
        Map<String, Object> expectedData = BookerTestData.
                expectedDataMethod("John", "Doe", 15,
                        true, bookingDatesMap, "Lunch");

        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //do assertion
        Map<String, Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(actualData.get("booking.firstname"), expectedData.get("booking.firstname"));
        Assert.assertEquals(actualData.get("booking.lastname"), expectedData.get("booking.lastname"));
        Assert.assertEquals(actualData.get("booking.totalprice"), expectedData.get("booking.totalprice"));
        Assert.assertEquals(actualData.get("booking.depositpaid"), expectedData.get("booking.depositpaid"));
        Assert.assertEquals(actualData.get("booking.bookingdates.checkin"), expectedData.get("booking.bookingdates.checkin"));
        Assert.assertEquals(actualData.get("booking.bookingdates.checkout"), expectedData.get("booking.bookingdates.checkout"));
        Assert.assertEquals(actualData.get("booking.additionalneeds"), expectedData.get("booking.additionalneeds"));


    }
}
