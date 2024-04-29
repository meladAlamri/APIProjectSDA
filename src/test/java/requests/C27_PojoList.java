package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import pojos.JsonPlaceHolderPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C27_PojoList extends JsonPlaceHolderBaseUrl {
    /*
       Given
           https://jsonplaceholder.typicode.com/todos
       When
           I send a GET request to the Url
       Then
           HTTP Status Code should be 200
       And
           There must be a todo like:
               {
                   "userId": 1,
                   "id": 4,
                   "title": "et porro tempora",
                   "completed": true
               }
    */
    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data
        //set the expected data
        String strJson = """
                {
                    "userId": 1,
                    "id": 4,
                    "title": "et porro tempora",
                    "completed": true
                    }""";


        JsonPlaceHolderPojo expectedData =ObjectMapperUtils.convertJsonToPojo(strJson,  JsonPlaceHolderPojo.class);
        System.out.println("expectedData = " + expectedData);

        //set the request and got the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();



        // do assertion
        response
                .then()
                .statusCode(200)
                .body("userId[3]", equalTo(expectedData.getUserId()),
                        "title[3]", equalTo(expectedData.getTitle()),
                        "completed[3]", equalTo(expectedData.getCompleted()))
        ;

    }
}
