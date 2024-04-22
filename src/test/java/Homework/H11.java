package Homework;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class H11 {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12

    Note: Print using JsonPath: response.jsonPath().prettyPrint();

*/
    @Test
    public void test() {
        //set the url
        String url = "https://automationexercise.com/api/productsList";

        //Send the request and get the response
        Response response = given().get(url);
        response.jsonPath().prettyPrint();

        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(200);

        JsonPath jsonPath = new JsonPath(response.asString()).setRootPath("products.category.usertype");
        int usertype = jsonPath.getInt("findAll{it.usertype == 'Women'}.size()");
        System.out.println("usertype = " + usertype);
        Assert.assertEquals(usertype, 12);


    }
}
