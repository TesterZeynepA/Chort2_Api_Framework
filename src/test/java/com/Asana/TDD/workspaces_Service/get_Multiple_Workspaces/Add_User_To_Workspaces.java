package com.Asana.TDD.workspaces_Service.get_Multiple_Workspaces;

import com.Asana.TDD.Hooks;
import com.Asana.endPoints.Workspaces;
import org.testng.annotations.Test;
import com.Asana.TDD.Hooks;
import com.Asana.endPoints.Workspaces;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Add_User_To_Workspaces extends Hooks {

        /**
         *
         * */

        @Test
        public void addUserToWorkspace() {

            response = Workspaces.add_A_User_To_A_Workspace_Or_Organization("1205485861998849", "1205415657858325");
            response.then()
                    .contentType(ContentType.JSON)
                    .body("data.gid", is("1205415657858325"))
                    .statusCode(200);

        }







}
