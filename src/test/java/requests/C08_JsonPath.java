package requests;

import base_urls.BookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C08_JsonPath extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/32
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
        {
            "firstname": "Josh",
            "lastname": "Allen",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "super bowls"
        }
*/


    @Test
    public void test() {

        //Set the url
        spec.pathParams("first", "booking",
                "second", "32");

        // Set expected data

        // Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        // Do assertion
        //1ed way
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Josh"),
                        "lastname", equalTo("Allen"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01")
                        // "additionalneeds", equalTo("super bowls")
                );

        //2nd way
        JsonPath jsonPath = response.jsonPath();

        String firstname = jsonPath.getString("firstname");
        String lastname = jsonPath.getString("lastname");
        int totalprice = jsonPath.getInt("totalprice");
        boolean depositpaid = jsonPath.getBoolean("depositpaid");
        String checkin = jsonPath.getString("bookingdates.checkin");
        String checkout = jsonPath.getString("bookingdates.checkout");
        String additionalneeds = jsonPath.getString("additionalneeds");

        System.out.println("firstname = " + firstname);
        System.out.println("lastname = " + lastname);
        System.out.println("totalprice = " + totalprice);
        System.out.println("depositpaid = " + depositpaid);
        System.out.println("checkin = " + checkin);
        System.out.println("checkout = " + checkout);
        System.out.println("additionalneeds = " + additionalneeds);

        // hard assert
        Assert.assertEquals(firstname, "Josh", "Firstname is not correct");
        Assert.assertEquals(lastname, "Allen", "Lastname is not correct");
        Assert.assertEquals(totalprice, 111, "Total price is not correct");
        Assert.assertTrue(depositpaid, "Deposit paid is not correct");
        Assert.assertEquals(checkin, "2018-01-01", "Checkin is not correct");
        Assert.assertEquals(checkout, "2019-01-01", "Checkout is not correct");
        Assert.assertEquals(additionalneeds, "midnight snack", "Additional needs is not correct");

        // soft assert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(firstname, "Josh", "Firstname is not correct");
        softAssert.assertEquals(lastname, "Allen", "Lastname is not correct");
        softAssert.assertEquals(totalprice, 111, "Total price is not correct");
        softAssert.assertTrue(depositpaid, "Deposit paid is not correct");
        softAssert.assertEquals(checkin, "2018-01-01", "Checkin is not correct");
        softAssert.assertEquals(checkout, "2019-01-01", "Checkout is not correct");
        softAssert.assertEquals(additionalneeds, "midnight snack", "Additional needs is not correct");
        softAssert.assertAll();


    }
}
