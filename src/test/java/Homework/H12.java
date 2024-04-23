package Homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojos.PetCategoryPojo;
import pojos.PetStorePetPojo;
import pojos.PetTagPojo;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class H12 {
    /**
     * //Write an automation test that will create, read, update, delete a 'pet'
     * using the "https://petstore.swagger.io/" document
     * (All actions must be done on same pet)
     * (Use Pojo)
     */
    private final String url = "https://petstore.swagger.io/v2/pet";
    private PetCategoryPojo petCategoryPojo;
    private PetTagPojo petTagPojo;
    private PetStorePetPojo expectedData;


    @BeforeClass
    public void beforeMethod() {
        //set the expected data
        long id = System.currentTimeMillis();
        petCategoryPojo = new PetCategoryPojo(id, "cat");
        ArrayList<String> photoUrlList = new ArrayList<>();
        photoUrlList.add("uuu");
        petTagPojo = new PetTagPojo(id, "cute");
        ArrayList<PetTagPojo> tagsList = new ArrayList<>();
        tagsList.add(petTagPojo);
        expectedData = new PetStorePetPojo(id, petCategoryPojo, "Sukar", photoUrlList, tagsList, "available");


    }

    @Test
    public void createPetTest() {

        //Send the request and get the response
        System.out.println("expectedData = " + expectedData);
        Response response = given()
                .body(expectedData)
                .contentType(ContentType.JSON)
                .post(url);
        response.jsonPath().prettyPrint();

        //do assertion
        responseAssertion(response);


    }

    @Test(dependsOnMethods = "createPetTest")
    public void readPetTest() {
        //Send the request and get the response
        Response response = given()
                .contentType(ContentType.JSON)
                .get(url + "/" + expectedData.getId());
        response.jsonPath().prettyPrint();

        //do assertion
        responseAssertion(response);



    }

    @Test(dependsOnMethods = "readPetTest")
    public void updatePetTest() {
        //update the expected data
        expectedData.setName("Ace");
        expectedData.getCategory().setName("big cat");
        expectedData.getPhotoUrls().add("ooo");
        expectedData.getTags().getFirst().setName("white");


        //Send the request and get the response
        System.out.println("expectedData = " + expectedData);
        Response response = given()
                .body(expectedData)
                .contentType(ContentType.JSON)
                .put(url);
        response.jsonPath().prettyPrint();

        //do assertion
        responseAssertion(response);

    }

    @Test(dependsOnMethods = "updatePetTest")
    public void deletePetTest() {

        //Send the request and get the response
        Response response = given()
                .contentType(ContentType.JSON)
                .delete(url + "/" + expectedData.getId());
        response.jsonPath().prettyPrint();

        //do assertion
        response
                .then()
                .statusCode(200)
                .body("code", equalTo(response.getStatusCode())
                        , "type", equalTo("unknown")
                        , "message", equalTo(expectedData.getId().toString())
                );
    }

    private void responseAssertion( Response response){
        response
                .then()
                .statusCode(200)
                .body("id", equalTo(expectedData.getId())
                        , "category.id", equalTo(petCategoryPojo.getId())
                        , "category.name", equalTo(expectedData.getCategory().getName())
                        , "name", equalTo(expectedData.getName())
                        , "photoUrls", equalTo(expectedData.getPhotoUrls())
                        , "tags.id[0]", equalTo(petTagPojo.getId())
                        , "tags.name[0]", equalTo(expectedData.getTags().getFirst().getName())
                        , "status", equalTo(expectedData.getStatus())
                );
    }
}
