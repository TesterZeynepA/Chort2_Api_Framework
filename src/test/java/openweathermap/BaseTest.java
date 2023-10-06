package openweathermap;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

public class BaseTest {

    protected static final String API_KEY = "a16e33bdb752c9b5778d38c42614a6e4";

    private final static String URI= "https://api.openweathermap.org/data/2.5";

    public void setUp(){
        RestAssured.baseURI=URI;

        enableLoggingOfRequestAndResponseIfValidationFails();
    }




}
