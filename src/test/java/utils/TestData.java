package utils;

public class TestData {

    private String validCountry = "us";
    private String validPostalCode = "90210";

    private String invalidCountry = "xx";
    private String invalidPostalCode = "00000";

    public String getValidCountry() {
        return validCountry;
    }

    public String getValidPostalCode() {
        return validPostalCode;
    }

    public String getInvalidCountry() {
        return invalidCountry;
    }

    public String getInvalidPostalCode() {
        return invalidPostalCode;
    }
}