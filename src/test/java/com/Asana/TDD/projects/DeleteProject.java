package com.Asana.TDD.projects;

import com.Asana.TDD.Hooks;
import com.Asana.endPoints.Project;
import com.Asana.utilities.Dummy;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteProject extends Hooks {

    @Test( groups = "CreateProject")
    public void deleteProject(ITestContext context) {
        System.out.println("Delete test method working");
        String id = Dummy.getDummy("projectGid");
        response = Project.delete_A_Project(id);

        response.prettyPrint();
    }
}
