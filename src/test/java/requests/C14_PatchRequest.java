package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C14_PatchRequest extends JsonPlaceHolderBaseUrl {

    /**
     * Given
     * 1) https://jsonplaceholder.typicode.com/todos/198
     * 2) {
     * "title": "Read Books"
     * }
     * When
     * I send PATCH Request to the Url
     * Then
     * Status code is 200
     * And response body is like  {
     * "userId": 10,
     * "id": 198,
     * "title": "Read Books",
     * "completed": true
     * }
     */
    @Test
    public void test() {
        //set the url
        spec.pathParams("first", "todos",
                "second", "198");

        //set the expected data
        Map<String, Object> expectedData = JsonPlaceHolderTestData.expectedDataMap(null, "Read Books", null);
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map<String, Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(actualData.get("title"), expectedData.get("title"));

        Assert.assertEquals(actualData.get("userId"), 10);
        Assert.assertEquals(actualData.get("completed"), true);

    }
}
