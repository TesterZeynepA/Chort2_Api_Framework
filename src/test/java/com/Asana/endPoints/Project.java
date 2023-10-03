package com.Asana.endPoints;

import com.Asana.TDD.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Project {

    private static String CREATE_A_PROJECT = "/api/1.0/projects";
    private static String DELETE_A_PROJECT = "/api/1.0/projects/{project_gid}";



    public static Response create_A_Project(Object body,Object... queryParams) {
        Map<String, Object> myParams = new LinkedHashMap<>();
        for (int i = 0; i < queryParams.length - 1; i++) {
            myParams.put(queryParams[i].toString(), queryParams[i + 1]);
            i = i + 1;
        }

        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("authorization", BaseClass.token)
                .queryParams(myParams)
                .body(body)
                //.log().all()
                .when()
                .post(CREATE_A_PROJECT);


        return response;
    }



    public static Response delete_A_Project(String project_gid) {

        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("authorization", BaseClass.token)
                .pathParam("project_gid",project_gid)
                .when()
                .delete(DELETE_A_PROJECT);


        return response;

    }
}
