package requests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C04_AssertBodyHamcrest {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User sends a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response format should be "application/json"
        And
            "title" is "et itaque necessitatibus maxime molestiae qui quas velit"
        And
            "completed" is false
        And
            "userId" is 2
    */
    /**
     * Steps to follow in API Testing:
     *     1. Set the URL
     *     2. Set the expected data
     *     3. Send the request and get the response
     *     4. Do Assertion
     *     */

    @Test
    public void assertBodyMethod() {

//        1. Set the URL
//        https://jsonplaceholder.typicode.com/todos/23
        String url = " https://jsonplaceholder.typicode.com/todos/23";

//        2. Set the expected data --> We will do this in post and put requests
//        3. Send the request and get the response
//        User sends a GET request to the URL
        Response response = given().get(url);
        response.prettyPrint();

//         4. Do Assertion
//        HTTP Status Code should be 200
//        Response format should be "application/json"
//        "title" is "et itaque necessitatibus maxime molestiae qui quas velit"
//        "completed" is false
//        "userId" is 2

        //1st way -> hard
        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed",equalTo(false))
                .body("userId",equalTo(2));

        //2nd way -> soft
        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false)
                        ,"userId",equalTo(2));



    }

}

