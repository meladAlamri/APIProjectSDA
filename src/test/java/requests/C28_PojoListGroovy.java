package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;
import utilities.ObjectMapperUtils;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C28_PojoListGroovy extends JsonPlaceHolderBaseUrl {
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
    public void test() throws JsonProcessingException {
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


        JsonPlaceHolderPojo expectedData = ObjectMapperUtils.convertJsonToPojo(strJson, JsonPlaceHolderPojo.class);
        System.out.println("expectedData = " + expectedData);

        //set the request and got the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();


        // do assertion
        Object todo = response.jsonPath().getList("findAll{it.id == 4}").getFirst();
        System.out.println("todo = " + todo);
        String actualData = new ObjectMapper().writeValueAsString(todo);
        System.out.println("actualData = " + actualData);
        JsonPlaceHolderPojo objectMapper = ObjectMapperUtils.convertJsonToPojo(actualData, JsonPlaceHolderPojo.class);
        response
                .then()
                .statusCode(200);

        Assert.assertEquals(objectMapper.getUserId(), expectedData.getUserId());
        Assert.assertEquals(objectMapper.getTitle(), expectedData.getTitle());
        Assert.assertEquals(objectMapper.getCompleted(), expectedData.getCompleted());

    }
}
