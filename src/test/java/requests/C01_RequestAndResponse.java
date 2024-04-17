package requests;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class C01_RequestAndResponse {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
    And
        print connection and Server header on console
     And
        print all header on console
*/
    public static void main(String[] args) {

//        https://restful-booker.herokuapp.com/booking

//        User sends a GET Request to the url
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        // response.prettyPrint(); // to see body in pretty way

//        HTTP Status Code should be 200
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);
        //response.then().assertThat().statusCode(200);

//        Content Type should be JSON
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);

//        Status Line should be HTTP/1.1 200 OK
        String statusLine = response.statusLine();
        System.out.println("statusLine = " + statusLine);

//        print connection and Server header on console
        String connection = response.header("Connection");
        String server = response.header("Server");
        System.out.println("\nconnection = " + connection);
        System.out.println("\nserver = " + server);

//        print all header on console
        Headers headers = response.headers();
        System.out.println("\nheaders = " + headers);

    }
}
