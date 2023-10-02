package com.Asana.TDD.workspaces_Service.get_Multiple_Workspaces;

import com.Asana.TDD.Hooks;
import com.Asana.endPoints.Workspaces;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get_Workspaces extends Hooks {


    @Test
    public void get_WorkSpaces() {

        response = Workspaces.get_Multiple_Workspaces("limit",1,"opt_pretty",true);

        response.prettyPrint();
    }


}
