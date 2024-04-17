package Homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class H04 {

    /**
     *  Given <a href="https://reqres.in/api/users/2">reqres.in</a><br>
     *  When User send GET Request to the URL<br>
     *  Then HTTP Status Code should be 200<br>
     *  And Response format should be "application/json"<br>
     *  And "email" is "janet.weaver@reqres.in",<br>
     *  And "first_name" is "Janet"<br>
     *  And "last_name" is "Weaver"<br>
     *  And "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
     */

    @Test
    public void test() {
//        1. Set the URL
        String url = "https://reqres.in/api/users/2";

//        2. Set the expected data
//        3. Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

//        4. Do Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.email",equalTo("janet.weaver@reqres.in")
                ,"data.first_name",equalTo("Janet")
                ,"data.last_name",equalTo("Weaver")
                ,"support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
}
