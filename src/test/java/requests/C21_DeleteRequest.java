package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C21_DeleteRequest extends JsonPlaceHolderBaseUrl {

/*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        I send DELETE Request to the Url
    Then
        Status code is 200
        And Response body is { }
*/

    @Test
    public void deleteRequestTest(){
        //Set the url
        spec.pathParams("first","todos","second","198");

        //set the request and got the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .statusCode(200)
                .body("isEmpty()", Matchers.is(true));






    }

}
