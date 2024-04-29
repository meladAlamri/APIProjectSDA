package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C24_ObjectMapperPostPojo extends JsonPlaceHolderBaseUrl {

    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
               "userId": 55,
               "title": "Tidy your room",
               "completed": false
               }


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
    public void test() throws JsonProcessingException {
        //set the url
        spec.pathParams("first", "todos");

        // set the expected data
        String strJson = """
                {   "userId": 55,
                    "title": "Tidy your room",
                     "completed": false
                }
                """;

        JsonPlaceHolderPojo expectedData = new ObjectMapper().readValue(strJson, JsonPlaceHolderPojo.class);
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(201)
                .body("userId", equalTo(expectedData.getUserId()),
                        "title", equalTo(expectedData.getTitle()),
                        "completed", equalTo(expectedData.getCompleted()));

    }
}
