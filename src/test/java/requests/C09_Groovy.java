package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class C09_Groovy extends JsonPlaceHolderBaseUrl {
    /**
        Given
            https://jsonplaceholder.typicode.com/todos
        When
             I send GET Request to the URL
        Then
             1)Status code is 200
             2)Print all ids greater than 190 on the console
               Assert that there are 10 ids greater than 190
             3)Print all completes whose ids are less than 5 on the console
               Assert that the number of userIds whose ids are less than 5 is 4
             4)Print all titles whose ids are greater than 195
               Assert that "quis eius est sint explicabo" is one of the titles whose id is less than 5
             5)Print id whose title is "quo adipisci enim quam ut ab"
               Assert that id is 8
    */

    @Test
    public void test() {

        //set the url
        spec.pathParam("first","todos");

        //set the expected data

        //set the request and got the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        // do assertion
        JsonPath jsonPath = response.jsonPath();

        //1
        response.then().statusCode(200);

        //2
        List<Integer> idList = jsonPath.getList("id");
        System.out.println(idList);
        int countGredThen190 = 0;

        for (int w : idList) {
            if (w > 190) {
                System.out.println(w);
                countGredThen190++;

            }
        }
        Assert.assertEquals(countGredThen190, 10);

        //3
        List<Boolean> complitedList = jsonPath.getList("findAll{it.id < 5}.completed");
        System.out.println("completedList = " + complitedList);
        Assert.assertEquals(complitedList.size(),4);

        //4
        List <String> titleList = jsonPath.getList("findAll{it.id > 195}.title");
        System.out.println("titleList = " + titleList);

        Assert.assertTrue(titleList.contains("quis eius est sint explicabo"));

        //5
        int idIndex = (int) jsonPath.getList("findAll{it.title == 'quo adipisci enim quam ut ab' }.id").getFirst();
        System.out.println("idIndex = " + idIndex);
        Assert.assertEquals(idIndex,8);




    }
}
