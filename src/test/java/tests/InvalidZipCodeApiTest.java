package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.TestData;

public class InvalidZipCodeApiTest extends BaseTest {

    private TestData testData;

    @BeforeClass
    public void prepareTestData() {
        testData = new TestData();
    }

    @Test
    public void verifyInvalidCountry() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .pathParam("country", testData.getInvalidCountry())
                        .pathParam("postalCode", testData.getValidPostalCode())
                        .when()
                        .get("/{country}/{postalCode}");

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void verifyInvalidPostalCode() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .pathParam("country", testData.getValidCountry())
                        .pathParam("postalCode", testData.getInvalidPostalCode())
                        .when()
                        .get("/{country}/{postalCode}");

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void verifyInvalidCountryAndPostalCode() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .pathParam("country", testData.getInvalidCountry())
                        .pathParam("postalCode", testData.getInvalidPostalCode())
                        .when()
                        .get("/{country}/{postalCode}");

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void verifyEmptyCountry() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .when()
                        .get("//90210");

        Assert.assertTrue(
                response.getStatusCode() >= 400
        );
    }

    @Test
    public void verifyEmptyPostalCode() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .when()
                        .get("/us/");

        Assert.assertTrue(
                response.getStatusCode() >= 400
        );
    }
}