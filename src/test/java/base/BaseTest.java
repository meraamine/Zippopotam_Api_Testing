package base;

import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected String baseUrl;

    @BeforeClass
    public void setUp() {
        baseUrl = "https://api.zippopotam.us";
    }
}