package com.Asana.endPoints;
import com.Asana.TDD.BaseClass;
import com.Asana.utilities.ApiUtil;
import io.cucumber.java.it.Ma;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class Workspaces {
    private static final String Get_Multiple_Workspaces = "/api/1.0/workspaces";

    private static final String Get_A_Workspace = "/api/1.0/workspaces/{workspace_gid}";

    private static final String Update_A_Workspace = "/api/1.0/workspaces/{workspace_gid}";

    private static final String Add_A_User_To_A_Workspace_Or_Organization = "/api/1.0/workspaces/{workspace_gid}/addUser";

    private static final String Remove_A_User_From_A_Workspace_Or_Organization = "/api/1.0/workspaces/{workspace_gid}/removeUser";


    //limit, 3, pretty, true


    // limit, 3 , pretty, true
    public static Response get_Multiple_Workspaces(Object... queryParams) {
        Map<String, Object> myParams = new LinkedHashMap<>();
        for (int i = 0; i < queryParams.length - 1; i++) {
            myParams.put(queryParams[i].toString(), queryParams[i + 1]);
            i = i + 1;
        }


        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParams(myParams)
                .header("authorization", BaseClass.token)
                .when()
                .get(Get_Multiple_Workspaces);

        return response;
    }
}