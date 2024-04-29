package Homework.H13;

import org.testng.annotations.BeforeMethod;
import pojos.UserPetStorePojo;
import utilities.ObjectMapperUtils;

public class setUp {
    //Write an automation test that will create a 'user' then read,
    // update and delete the created user using the "https://petstore.swagger.io/" document.
    // (Create a classes for each request.)
    UserPetStorePojo Data;
    //Set the url
    String url = "https://petstore.swagger.io/v2/user";
    String type = "unknown";

    @BeforeMethod
    public void setUp() {

        String strJson =
                """
                        {
                          "id": 999,
                          "username": "Dany",
                          "firstName": "Melad",
                          "lastName": "Alamri",
                          "email": "melad@gmail.com",
                          "password": "12345",
                          "phone": "999999",
                          "userStatus": 0
                        }""";


        //set the request and got the response
        Data = ObjectMapperUtils.convertJsonToPojo(strJson, UserPetStorePojo.class);
    }



}
