package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C10_PostRequest extends JsonPlaceHolderBaseUrl {
    /**
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url

        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
    */

    @Test
    public void test() {

        spec.pathParam("first","todos");

        //set the expected data(Payload)
        String expectedData = """
                        {
                        "userId": 55,
                        "title": "Tidy your room",
                        "completed": false
                        }""";

        //set the request and got the response
        Response response = given(spec)
                .body(expectedData)
                .post("{first}");
        response.prettyPrint();

        // do assert
        response
                .then()
                .assertThat()
                .statusCode(201)
                .body("userId",equalTo(55),
                        "title",equalTo("Tidy your room"),
                        "completed",equalTo(false));
    }
}
