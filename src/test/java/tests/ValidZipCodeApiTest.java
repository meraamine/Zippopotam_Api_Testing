package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestData;

import java.util.List;
import java.util.Map;

public class ValidZipCodeApiTest extends BaseTest {

    private Response getValidPostalCodeResponse() {

        return RestAssured.given()
                .baseUri(baseUrl)
                .pathParam("country", TestData.VALID_COUNTRY)
                .pathParam("postalCode", TestData.VALID_POSTAL_CODE)
                .when()
                .get("/{country}/{postalCode}");
    }

    @Test
    public void verifyValidPostalCodeResponse() {

        Response response = getValidPostalCodeResponse();

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected status code to be 200"
        );

        Assert.assertTrue(
                response.getContentType().contains("application/json"),
                "Expected response content type to be JSON"
        );

        Map<String, Object> responseBody =
                response.jsonPath().getMap("");

        Assert.assertNotNull(
                responseBody.get("post code"),
                "Post code should exist in response"
        );

        Assert.assertEquals(
                responseBody.get("post code").toString(),
                TestData.VALID_POSTAL_CODE,
                "Returned postal code is incorrect"
        );

        Assert.assertNotNull(
                responseBody.get("country"),
                "Country should exist in response"
        );

        Assert.assertEquals(
                responseBody.get("country abbreviation").toString(),
                "US",
                "Country abbreviation is incorrect"
        );

        Assert.assertNotNull(
                responseBody.get("places"),
                "Places should exist in response"
        );
    }

    @Test
    public void verifyPlacesInResponse() {

        Response response = getValidPostalCodeResponse();

        List<Map<String, Object>> places =
                response.jsonPath().getList("places");

        Assert.assertNotNull(
                places,
                "Places list should not be null"
        );

        Assert.assertFalse(
                places.isEmpty(),
                "Places list should not be empty"
        );

        Object placeName =
                places.getFirst().get("place name");

        Assert.assertNotNull(
                placeName,
                "Place name should exist"
        );

        Assert.assertFalse(
                placeName.toString().isBlank(),
                "Place name should not be empty"
        );
    }
}
