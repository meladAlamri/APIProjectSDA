package utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateToken() {
        String strJson = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }""";
        Response response = given()
                .contentType("application/json")
                .body(strJson)
                .post("https://restful-booker.herokuapp.com/auth");

        return response.jsonPath().getString("token");

    }

}
