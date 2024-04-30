package requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C29_GroovyGetRequest extends GoRestBaseUrl {
      /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Abhaidev Kaur", "Fr. Deenabandhu Adiga", "Akshita Singh DC" are among the users -> This may change
        And
            The female users are less than or equals to male users -> This may change
*/


    @Test
    public void groovyTest() {

        //Set the url
        spec.pathParam("first", "users");

        //set the request and got the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .statusCode(200)
                .body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Girindra Pilla", "Preity Abbott", "Vaishno Nehru"));


        int maleCount = response.jsonPath().getInt("data.findAll{it.gender == 'male'}.size()");
        int femaleCount = response.jsonPath().getInt("data.findAll{it.gender == 'female'}.size()");
        Assert.assertTrue(femaleCount <= maleCount, "femaleCount > maleCount");
    }


}
