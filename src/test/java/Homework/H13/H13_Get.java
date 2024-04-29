package Homework.H13;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class H13_Get extends setUp {
    //Write an automation test that will create a 'user' then read,
    // update and delete the created user using the "https://petstore.swagger.io/" document.
    // (Create a classes for each request.)


    @Test()
    public void readUserTest() {


        System.out.println("Data = " + Data);

        Response response = given()
                .contentType(ContentType.JSON)
                .get(url + "/" + Data.getUsername());
        response.prettyPrint();

        //do assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(Data.getId())
                        , "username", equalTo(Data.getUsername())
                        , "firstName", equalTo(Data.getFirstName())
                        , "lastName", equalTo(Data.getLastName())
                        , "email", equalTo(Data.getEmail())
                        , "password", equalTo(Data.getPassword())
                        , "phone", equalTo(Data.getPhone())
                        , "userStatus", equalTo(0)
                );
    }


}
