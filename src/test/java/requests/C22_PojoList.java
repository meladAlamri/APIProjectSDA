package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C22_PojoList extends JsonPlaceHolderBaseUrl {
    /*
   Given
       https://jsonplaceholder.typicode.com/todos
   When
      I send a GET request to the Url
   And
       Accept type is “application/json”
   Then
       HTTP Status Code should be 200
   And
       First todo must be like:
               {
                   "userId": 1,
                       "id": 1,
                       "title": "delectus aut autem",
                       "completed": false
               }
*/
    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(1, "delectus aut autem", false);
        System.out.println("expectedData = " + expectedData);

        //set the request and got the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();


        // do assertion
        response
                .then()
                .statusCode(200)
                .body("userId[0]", equalTo(expectedData.getUserId()),
                        "title[0]", equalTo(expectedData.getTitle()),
                        "completed[0]", equalTo(expectedData.getCompleted()))
        ;

    }
}
