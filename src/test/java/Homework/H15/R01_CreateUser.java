package Homework.H15;

import base_urls.UserContactsBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserContactsPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class R01_CreateUser extends UserContactsBaseUrl {

    public static String userId;
    public static String token = "";
    public static int version;
    public static UserContactsPojo expectedData;
    @Test
    public void createUserTest() {
        //set the url
        spec.pathParam("first", "users");

        //Set the exacted data
        String strJson = """
                {
                    "firstName": "Melad",
                    "lastName": "Ala",
                    "email": "melad@fake.com",
                    "password": "myPassword"
                }""";

        expectedData = ObjectMapperUtils.convertJsonToPojo(strJson, UserContactsPojo.class);

        //send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        userId = response.jsonPath().getString("user._id");
        token = response.jsonPath().getString("token");
        version = response.jsonPath().getInt("user.__v");

        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(201)
                .body("user.firstName", equalTo(expectedData.getFirstName()),
                        "user.lastName",equalTo(expectedData.getLastName()),
                        "user.email",equalTo(expectedData.getEmail()));


    }
}
