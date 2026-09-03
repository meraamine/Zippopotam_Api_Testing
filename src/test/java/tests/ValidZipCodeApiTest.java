package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.TestData;

import java.util.List;
import java.util.Map;

public class ValidZipCodeApiTest extends BaseTest {

    private TestData testData;

    @BeforeClass
    public void prepareTestData() {
        testData = new TestData();
    }

    @Test
    public void verifyValidPostalCodeRequest() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .pathParam("country", testData.getValidCountry())
                        .pathParam("postalCode", testData.getValidPostalCode())
                        .when()
                        .get("/{country}/{postalCode}");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void verifyResponseCountry() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .pathParam("country", testData.getValidCountry())
                        .pathParam("postalCode", testData.getValidPostalCode())
                        .when()
                        .get("/{country}/{postalCode}");

        Map<String, Object> responseBody =
                response.jsonPath().getMap("");

        String countryAbbreviation =
                responseBody.get("country abbreviation").toString();

        Assert.assertEquals(countryAbbreviation, "US");
    }

    @Test
    public void verifyResponsePostalCode() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .pathParam("country", testData.getValidCountry())
                        .pathParam("postalCode", testData.getValidPostalCode())
                        .when()
                        .get("/{country}/{postalCode}");

        Map<String, Object> responseBody =
                response.jsonPath().getMap("");

        String postalCode =
                responseBody.get("post code").toString();

        Assert.assertEquals(
                postalCode,
                testData.getValidPostalCode()
        );
    }

    @Test
    public void verifyPlacesExistInResponse() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .pathParam("country", testData.getValidCountry())
                        .pathParam("postalCode", testData.getValidPostalCode())
                        .when()
                        .get("/{country}/{postalCode}");

        List<Map<String, Object>> places =
                response.jsonPath().getList("places");

        Assert.assertNotNull(places);
    }

    @Test
    public void verifyPlacesAreNotEmpty() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .pathParam("country", testData.getValidCountry())
                        .pathParam("postalCode", testData.getValidPostalCode())
                        .when()
                        .get("/{country}/{postalCode}");

        List<Map<String, Object>> places =
                response.jsonPath().getList("places");

        Assert.assertFalse(places.isEmpty());
    }

    @Test
    public void verifyPlaceNameExists() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .pathParam("country", testData.getValidCountry())
                        .pathParam("postalCode", testData.getValidPostalCode())
                        .when()
                        .get("/{country}/{postalCode}");

        List<Map<String, Object>> places =
                response.jsonPath().getList("places");

        String placeName =
                places.getFirst().get("place name").toString();

        Assert.assertNotNull(placeName);
        Assert.assertFalse(placeName.isEmpty());
    }

    @Test
    public void verifyResponseContentType() {

        Response response =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .pathParam("country", testData.getValidCountry())
                        .pathParam("postalCode", testData.getValidPostalCode())
                        .when()
                        .get("/{country}/{postalCode}");

        Assert.assertTrue(
                response.getContentType().contains("application/json")
        );
    }
}