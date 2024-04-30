package Homework.H13;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserPetStorePojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class H13_Put extends setUp {
    //Write an automation test that will create a 'user' then read,
    // update and delete the created user using the "https://petstore.swagger.io/" document.
    // (Create a classes for each request.)


    @Test
    public void updateUserTest() {


        String strJson1 =
                """
                        {
                          "id": 999,
                          "username": "Dany",
                          "firstName": "Dana",
                          "lastName": "Alamri",
                          "email": "Dana@gmail.com",
                          "password": "12345",
                          "phone": "888888",
                          "userStatus": 0
                        }""";


        //set the request and got the response
        UserPetStorePojo  Data1 = ObjectMapperUtils.convertJsonToPojo(strJson1, UserPetStorePojo.class);

        System.out.println("Data = " + Data1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(Data1)
                //.sessionId(session)
                .put(url + "/" + Data1.getUsername());
        response.prettyPrint();

        //do assertion
        response
                .then()
                .assertThat()
                .body("code", equalTo(response.getStatusCode())
                        , "type", equalTo(type)
                        , "message", equalTo(Data1.getId() + "")
                );

    }


}
