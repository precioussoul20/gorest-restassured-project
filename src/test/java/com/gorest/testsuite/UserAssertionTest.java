package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class UserAssertionTest extends TestBase {
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
        //1. Verify if the total record is 20
        response.body("size()", equalTo(20));
        //2. Verify if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
        response.body("[4].name", equalTo("Tanirika Khan"));
        //3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
        response.body("[7].name", equalTo("Indra Trivedi II"));
        //4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
        response.body("name", hasItems("Ekalavya Embranthiri", "Kailash Pillai", "Arnesh Singh"));
        //5. Verify the email of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
        response.body("[3].email", equalTo("kailash_pillai@hauck.test"));
        //6. Verify the status is “Active” of user name is “Amaresh Rana”
        response.body("[2].status", equalTo("active"));
        //7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”
        response.body("[1].gender", equalTo("male"));
    }
}