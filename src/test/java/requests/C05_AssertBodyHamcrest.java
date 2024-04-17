package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C05_AssertBodyHamcrest {
/*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        I send a GET request to the Url

    Then
        HTTP Status Code should be 200
    And
        Response format should be "application/json"
    And
        There should be 200 todos
    And
        "quis eius est sint explicabo" should be one of the todos title
    And
        2, 7, and 9 should be among the userIds
*/
    @Test
    public void testAssertBodyHamcrest() {

//         * Steps to follow in API Testing:
//         *     1. Set the URL
        String url = "https://jsonplaceholder.typicode.com/todos";

//         *     2. Set the expected data

//         *     3. Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

//         *     4. Do Assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("",hasSize(200),
                        "title",hasItem( "quis eius est sint explicabo"),
                        "userId",hasItems(2,7,9));


    }
}
