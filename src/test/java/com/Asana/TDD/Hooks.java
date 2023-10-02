package com.Asana.TDD;

import com.Asana.utilities.ConfigurationReader;
import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;

public class Hooks extends BaseClass{

    @BeforeClass
    public void setUp(){

        baseURI= ConfigurationReader.get("baseURL");

        token ="Bearer " + ConfigurationReader.get("token");
    }



}
