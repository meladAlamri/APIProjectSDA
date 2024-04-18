package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C11_PostRequestMap extends JsonPlaceHolderBaseUrl {
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
        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put( "userId", 55);
        expectedDataMap.put( "title", "Tidy your room");
        expectedDataMap.put( "completed", false);
        System.out.println("expectedDataMap = " + expectedDataMap);
        


        //set the request and got the response
        Response response = given(spec)
                .body(expectedDataMap)
                .post("{first}");
        response.prettyPrint();

        // do assert
        response
                .then()
                .assertThat()
                .statusCode(201)
                .body("userId",equalTo(expectedDataMap.get("userId")),
                        "title",equalTo(expectedDataMap.get("title")),
                        "completed",equalTo(expectedDataMap.get("completed")));
    }
}
