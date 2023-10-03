package com.Asana.TDD;

import com.Asana.pojo.Data;
import com.Asana.pojo.MainData;
import com.Asana.utilities.ConfigurationReader;
import static io.restassured.RestAssured.*;

import com.Asana.utilities.Dummy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Hooks extends BaseClass{

    @BeforeClass
    public void setUp(){

        baseURI= ConfigurationReader.get("baseURL");

        token ="Bearer " + ConfigurationReader.get("token");
    }

    @BeforeMethod(onlyForGroups = "CreateProject")
    public void setUpProject() {
        System.out.println("Before method working");
        Data data = Data.randomProject();
        MainData mainData = new MainData();
        mainData.setData(data);
        response = com.Asana.endPoints.Project.create_A_Project(mainData);
        String projectGid = response.path("data.gid");
        Dummy.setDummy("projectGid", projectGid);
    }




}
