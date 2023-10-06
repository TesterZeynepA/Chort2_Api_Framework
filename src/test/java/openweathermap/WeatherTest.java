package openweathermap;

import io.restassured.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WeatherTest extends BaseTest{

//https://api.openweathermap.org/data/2.5/weather?q=Bursa&units=metric&appid=a16e33bdb752c9b5778d38c42614a6e4

    @Test
    public void searchAndValidtyName(){

        given()
                .accept(ContentType.JSON)
                .basePath(("/weather"))
                .queryParam("q", "Bursa")
                .queryParam("units", "metric")
                .queryParam("appid", API_KEY)
                .when()
                .get("/weather")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", equalTo("Bursa"));


    }









}
