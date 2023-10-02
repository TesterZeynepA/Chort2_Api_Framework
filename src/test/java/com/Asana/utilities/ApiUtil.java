package com.Asana.utilities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.Asana.utilities.Driver.closeDriver;
import static com.Asana.utilities.Driver.getDriver;


public class ApiUtil {

//    public static String getTokenNormal() {
//        getDriver().get("https://a3m-dev.rxdrugshub.com");
//        getDriver().findElement(By.xpath("//*[@id='username']")).sendKeys(ConfigurationReader.get("email"));
//        getDriver().findElement(By.xpath("//*[@id='password']")).sendKeys(ConfigurationReader.get("password"), Keys.ENTER);
//        getDriver().navigate().to("https://dev.rxdrugshub.com/auth/userinfo");
//        String text = getDriver().findElement(By.xpath("//pre")).getText();
//
//        Gson gson = new Gson();
//        JsonObject jsonObject = gson.fromJson(text, JsonObject.class);
//        String token = jsonObject.get("access_token").getAsString();
//
//        closeDriver();
//
//        return "Bearer " + token;
//    }

    public static String exampleGetToken(){

        String token = "Bearer " + ConfigurationReader.get("token");
        return token;
    }



    public static String getToken() {
        String token = ConfigurationReader.get("token");
        if (tokenTimeCounter() > 30) {
            getDriver().get(Environment.URL);
            getDriver().findElement(By.xpath("//*[@id='username']")).sendKeys(Environment.EMAIL);
            getDriver().findElement(By.xpath("//*[@id='password']")).sendKeys(Environment.PASSWORD, Keys.ENTER);
            getDriver().navigate().to(Environment.TOKEN_URL);
            String text = getDriver().findElement(By.xpath("//pre")).getText();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(text, JsonObject.class);
            token = jsonObject.get("access_token").getAsString();

            closeDriver();
            ConfigurationReader.set("token", token);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ConfigurationReader.set("previousDateTime", LocalDateTime.now().format(formatter));
        }

        return "Bearer " + token;
    }

    public static long tokenTimeCounter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime previousDateTime = LocalDateTime.parse(ConfigurationReader.get("previousDateTime"), formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration duration = Duration.between(previousDateTime, currentDateTime);
        return duration.toMinutes();
    }

    public static JsonSchemaValidator schemaValidator(String schema) {
        JsonSchemaValidator jsonSchemaValidator = JsonSchemaValidator
                .matchesJsonSchemaInClasspath(schema);

        return jsonSchemaValidator;
    }

    public static String ObjectToJsonFormatter(Object object) {
        // handle the code
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String objectJson = gson.toJson(object);
        System.out.println(objectJson); // kullandiktan sonra kapat
        return objectJson;
    }

    public static void  requestBodyPrintOuter(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        String payLoad= null;
        try {
            payLoad = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(payLoad);
    }

    /**
     * this method reads json file and then updates with key and value parameters.
     */

    public static  <T> void updateJsonFile(String key, T value ,String filePath) {
        String jsonString = null;
        try {
            jsonString = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DocumentContext context = JsonPath.parse(jsonString.toString());
        context.set("$." + key, value);
        try {
            Files.write(Paths.get(filePath), context.jsonString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method reads json file and converts to string format to post json file.
     * */
    public String jsonFileStringify(String path) {
        String payload = null;
        try {
            payload = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return payload;
    }


}
