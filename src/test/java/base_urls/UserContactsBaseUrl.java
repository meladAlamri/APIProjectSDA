package base_urls;

import Homework.H15.R01_CreateUser;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class UserContactsBaseUrl {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {
        String token = R01_CreateUser.token;
        System.out.println("token = " + token);
        spec = new RequestSpecBuilder()
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .addHeader("Authorization", "Bearer " + token)
                .setContentType(ContentType.JSON)
                .build();

    }
}
