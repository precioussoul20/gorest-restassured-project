package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class UserExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
//        RestAssured.baseURI = "http://gorest.co.in";
//        RestAssured.basePath = "/public/v2";
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    @Test
    public void test01() {
        //1. Extract the All Ids
        List<Integer> listOfIds = response.extract().path("id");
        System.out.println("List of Ids are : " + listOfIds);
        //2. Extract the all Names
        List<String> listOfNames = response.extract().path("name");
        System.out.println("List of Names are : " + listOfNames);
        //3. Extract the name of 5th object
        String name = response.extract().path("[5].name");
        System.out.println("Name of the 5th object is : " + name);
        //4. Extract the names of all object whose status = inactive
        List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("Names of all objects whose status = inactive : " + names);
        //5. Extract the gender of all the object whose status = active
        List<String> genderOfObject = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("Gender of all objects whose status = active : " + genderOfObject);
        //6. Print the names of the object whose gender = female
        List<String> femaleNames = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("Names of the object whose gender = female : " + femaleNames);
        //7. Get all the emails of the object where status = inactive
        List<String> emails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("Get all Emails of the object where status = inactive : " + emails);
        //8. Get the ids of the object where gender = male
        List<Integer> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("Get all Ids of the object where gender = male : " + ids);
        //9. Get all the status
        List<String> status = response.extract().path("status");
        System.out.println("Get all the status : " + status);
        //10. Get email of the object where name = Lal Dwivedi
        String email = response.extract().path("[2].email");
        System.out.println("Email of the object where name = Bhadrak Singh : " + email);
        //11. Get gender of id = 5914189
        String gender = response.extract().path("[6].gender");
        System.out.println("Get email for " + gender);
    }
}
