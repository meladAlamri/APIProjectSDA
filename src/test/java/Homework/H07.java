package Homework;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


import static io.restassured.RestAssured.given;

public class H07 {
    /**
     * Given
     * <a href="https://reqres.in/api/unknown/">unknown</a><br>
     * When
     * I send GET Request to the URL<br>
     * Then
     * 1)Status code is 200<br>
     * 2)Print all pantone_values<br>
     * 3)Print all ids greater than 3 on the console<br>
     * Assert that there are 3 ids greater than 3<br>
     * 4)Print all names whose ids are less than 3 on the console<br>
     * Assert that the number of names whose ids are less than 3 is 2<br>
     */

    @Test
    public void test() {

        // set the url
        String url = "https://reqres.in/api/unknown/";

        //set the request and got the response
        Response response = given().get(url);
        response.prettyPrint();

        //do assertion

        //Status code is 200
        response
                .then()
                .assertThat()
                .statusCode(200);

        JsonPath jsonPath = new JsonPath(response.asString()).setRootPath("data");

        //2)Print all pantone_values
        List<String> pantoneValues = jsonPath.getList("pantone_value");
        System.out.println("pantoneValues = " + pantoneValues);

        //3)Print all ids greater than 3 on the console
        //Assert that there are 3 ids greater than 3
        List<Integer> idList = jsonPath.getList("findAll{it.id > 3}.id");
        System.out.println("idList = " + idList);
        Assert.assertEquals(idList.size(), 3);

        //4)Print all names whose ids are less than 3 on the console
        //Assert that the number of names whose ids are less than 3 is 2
        List<String> nameList = jsonPath.getList("findAll{it.id < 3}.name");
        System.out.println("nameList = " + nameList);
        Assert.assertEquals(nameList.size(), 2);


    }
}
