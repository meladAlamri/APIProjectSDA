package requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.GoRestPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;

public class C30_PojoGenerator extends GoRestBaseUrl {
    /*
Given
    https://gorest.co.in/public/v1/users?id=6880236
When
    User sends GET request
Then
    HTTP Status Code should be 200
And
    Response body should be like:
        {
            "meta": {
                "pagination": {
                    "total": 1,
                    "pages": 1,
                    "page": 1,
                    "limit": 10,
                    "links": {
                        "previous": null,
                        "current": "https://gorest.co.in/public/v1/users?page=1",
                        "next": null
                    }
                }
            },
            "data": [
                {
                    "id": 6880236,
                    "name": "Pres. Abhaya Sinha",
                    "email": "pres_sinha_abhaya@kovacek.test",
                    "gender": "male",
                    "status": "inactive"
                }
            ]
        }
 */
    @Test
    public void PojoGeneratorTest() {
        // Set the url
        spec.pathParam("first", "users")
                .queryParam("id", "6880236");

        // set the expected data

        String strJson = """
                {
                            "meta": {
                                "pagination": {
                                    "total": 1,
                                    "pages": 1,
                                    "page": 1,
                                    "limit": 10,
                                    "links": {
                                        "previous": null,
                                        "current": "https://gorest.co.in/public/v1/users?page=1",
                                        "next": null
                                    }
                                }
                            },
                            "data": [
                                {
                                    "id": 6880236,
                                    "name": "Pres. Abhaya Sinha",
                                    "email": "pres_sinha_abhaya@kovacek.test",
                                    "gender": "male",
                                    "status": "inactive"
                                }
                            ]
                        }""";
        GoRestPojo expectData = ObjectMapperUtils.convertJsonToPojo(strJson, GoRestPojo.class);
        System.out.println("expectData = " + expectData);

        //set the request and got the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //do assertion
        response.
                then()
                .statusCode(200)
                .body("meta.pagination.limit", equalTo(expectData.getMeta().getPagination().getLimit()),
                        "meta.pagination.links.current", equalTo(expectData.getMeta().getPagination().getLinks().getCurrent()),
                        "data.id[0]", equalTo(expectData.getData().getFirst().getId()),
                        "data.name[0]", equalTo(expectData.getData().getFirst().getName()),
                        "data.email[0]", equalTo(expectData.getData().getFirst().getEmail()),
                        "data.gender[0]", equalTo(expectData.getData().getFirst().getGender()),
                        "data.status[0]", equalTo(expectData.getData().getFirst().getStatus())

                        );


        ;

    }
}
