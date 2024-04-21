package requests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_data.BookerTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class C17_GetRequestMap extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/827
    When
        I send GET Request to the url
    Then
        Response body should be like that;
            {
                "firstname": "John",
                "lastname": "Smith",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
 */
    @Test
    public void getRequestMap() {
        //set the url
        spec.pathParams("first", "booking"
                , "second", "14");

        //set the expected data
        //First inner json as map
        Map<String, String> bookingDatesMap = BookerTestData
                .bookingDataMethod("2018-01-01", "2019-01-01");

        //Second outer json as map
        Map<String, Object> expectedData = BookerTestData.
                expectedDataMethod("John", "Smith", 111,
                        true, bookingDatesMap, "Breakfast");

        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given(spec)
                //.body(expectedData)
                .get("{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map<String, Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(actualData.get("firstname"), expectedData.get("firstname"));
        Assert.assertEquals(actualData.get("lastname"), expectedData.get("lastname"));
        Assert.assertEquals(actualData.get("totalprice"), expectedData.get("totalprice"));
        Assert.assertEquals(actualData.get("depositpaid"), expectedData.get("depositpaid"));
        Assert.assertEquals(actualData.get("bookingdates.checkin"), expectedData.get("bookingdates.checkin"));
        Assert.assertEquals(actualData.get("bookingdates.checkout"), expectedData.get("bookingdates.checkout"));
        Assert.assertEquals(actualData.get("additionalneeds"), expectedData.get("additionalneeds"));


    }
}
