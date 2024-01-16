package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
//        RestAssured.baseURI = "http://gorest.co.in";
//        RestAssured.basePath = "/public/v2";
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/post")
                .then().statusCode(200);
    }

    @Test
    public void test01() {

        //1. Verify if the total record is 25
        response.body("size", equalTo(25));

        //2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupticohaero libero.”
        response.body("[1].title" ,equalTo("Demitto conqueror atavus argumentum corrupti cohaero libero."));

        //3. Check the single user_id in the Array list (5914249)
        response.body("[2].user_id" ,equalTo(5914249));

        //4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
        response.body("user_id" ,hasItems(5914254 , 5914251 ,5914249));

        //5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.
        //Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
        //vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
        //tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
        //acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
        response.body("[5].body" ,equalTo("Demonstro cubo curia. Canonicus fuga arcus. Culpo sub rerum. " +
                "Armarium deporto peccatus. Arguo vilitas absens. Sublime crux suscipio. Eaque somnus recusandae. " +
                "Sursum usque deleo. Alioqui vacuus contigo. Pel vorax adduco. Deleniti velit suggero. Culpo cibo illo. " +
                "Volaticus altus constans."));
    }
}