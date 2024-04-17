package requests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertTrue;

public class C07_RequestSpecification extends BookerBaseUrl {
    /*
        Given
           https://restful-booker.herokuapp.com/booking
        When
           User sends a GET request to the URL
        Then
           Status code is 200
        And
           Among the data, there should be someone whose first name is "John" and last name is "Doe"
    */

    @Test
    public void requestSpecification() {

//        1. Set the URL
        spec.pathParam("first","booking").
                queryParams("firstname","John"
                        ,"lastname","Doe");

//        2. Set the expected data

//        3. Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

//        4. Do Assertion
        //1st way
        assertTrue(response.asString().contains("bookingid"));

        //2nd way
        response.then().body("", hasSize(greaterThan(0)));

    }
}
