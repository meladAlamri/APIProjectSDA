package Homework.H15;

import base_urls.UserContactsBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserContactsPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;

public class R04_DeleteUser extends UserContactsBaseUrl {


    @Test
    public void deleteUserTest() {
        //set the url
        spec.pathParams("first", "users","second","me");

        //send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();


        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(200);
        assertTrue(response.asString().isEmpty());



    }
}
