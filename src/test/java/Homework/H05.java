package Homework;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class H05 {

    /**
     * Given
     * <a href="https://reqres.in/api/users/23">reqres.in</a><br>
     * When
     * User send a GET Request to the url<br>
     * Then
     * HTTP Status code should be 404<br>
     * And
     * Status Line should be HTTP/1.1 404 Not Found<br>
     * And
     * Server is "cloudflare"<br>
     * And
     * Response body should be empty
     */

    @Test
    public void test() {
        //set the url
        String url = "https://reqres.in/api/users/23";

        //set the request and got the response
        Response response = given().get(url);
        response.prettyPrint();

        // do assertion
        response
                .then()
                .assertThat()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .header("Server", "cloudflare")
                .body("isEmpty()", Matchers.is(true));


    }
}
