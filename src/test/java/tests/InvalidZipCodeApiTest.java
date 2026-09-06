package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestData;

public class InvalidZipCodeApiTest extends BaseTest {

    private Response getResponse(String country, String postalCode) {

        return RestAssured.given()
                .baseUri(baseUrl)
                .pathParam("country", country)
                .pathParam("postalCode", postalCode)
                .when()
                .get("/{country}/{postalCode}");
    }

    @Test
    public void verifyInvalidCountry() {

        Response response =
                getResponse(
                        TestData.INVALID_COUNTRY,
                        TestData.VALID_POSTAL_CODE
                );

        Assert.assertEquals(
                response.getStatusCode(),
                404,
                "Expected 404 for invalid country"
        );
    }

    @Test
    public void verifyInvalidPostalCode() {

        Response response =
                getResponse(
                        TestData.VALID_COUNTRY,
                        TestData.INVALID_POSTAL_CODE
                );

        Assert.assertEquals(
                response.getStatusCode(),
                404,
                "Expected 404 for invalid postal code"
        );
    }

    @Test
    public void verifyInvalidCountryAndPostalCode() {

        Response response =
                getResponse(
                        TestData.INVALID_COUNTRY,
                        TestData.INVALID_POSTAL_CODE
                );

        Assert.assertEquals(
                response.getStatusCode(),
                404,
                "Expected 404 for invalid country and postal code"
        );
    }

    @Test
    public void verifyMissingCountry() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .when()
                        .get("/90210");

        Assert.assertTrue(
                response.getStatusCode() >= 400,
                "Expected client error for missing country"
        );
    }

    @Test
    public void verifyMissingPostalCode() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .when()
                        .get("/us");

        Assert.assertTrue(
                response.getStatusCode() >= 400,
                "Expected client error for missing postal code"
        );
    }
}
