package requests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertTrue;

public class C06_QueryParameters {
    /*
        Given
           https://restful-booker.herokuapp.com/booking
        When
           User sends a GET request to the URL
        Then
           Status code is 200
        And
           Among the data, there should be someone whose first name is "Jane" and last name is "Doe"
    */
    @Test
    public void QueryParametersTest() {

//        1. Set the URL
        String url = "https://restful-booker.herokuapp.com/booking?fristname=Jana&lastname=Doe";

//        2. Set the expected data
//        3. Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

//        4. Do Assertion
        response
                .then()
                .statusCode(200);
        //1st way
        assertTrue(response.asString().contains("bookingid"));

        //2nd way
        response.then().body("", hasSize(greaterThan(0)));

    }
}
