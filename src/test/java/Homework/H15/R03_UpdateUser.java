package Homework.H15;

import base_urls.UserContactsBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserContactsPojo;
import utilities.ObjectMapperUtils;

import static Homework.H15.R01_CreateUser.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class R03_UpdateUser extends UserContactsBaseUrl {


    @Test
    public void updateUserTest() {
        //set the url
        spec.pathParams("first", "users","second","me");


        //Set the exacted data
        String strJson = """
                {
                    "firstName": "Melad",
                    "lastName": "Ala",
                    "email": "melad@fake.com",
                    "password": "myPassword"
                }""";

        expectedData.setFirstName("Melo");
        expectedData.setLastName("Alamri");


        //send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();


        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("_id",equalTo(userId),
                        "firstName", equalTo(expectedData.getFirstName()),
                        "lastName",equalTo(expectedData.getLastName()),
                        "email",equalTo(expectedData.getEmail()),
                        "__v",equalTo(version));


    }
}
