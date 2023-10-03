package com.Asana.TDD.projects;

import com.Asana.TDD.Hooks;
import com.Asana.endPoints.Project;
import com.Asana.pojo.Data;
import com.Asana.pojo.MainData;
import com.Asana.utilities.Dummy;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Create_A_Project extends Hooks {

    @Test(priority = 1,groups = {"smoke","Reg1"})
    public void createProject(ITestContext context) {
        System.out.println("Working 1");
        Data data = new Data();
        data.setName("zeynep");
        data.setWorkspace("1205485861998849");
        data.setTeam("1205512931288990");

        MainData mainData = new MainData();
        mainData.setData(data);


        response = com.Asana.endPoints.Project.create_A_Project(mainData);


        String projectGid = response.path("data.gid");
        System.out.println("projectGid = " + projectGid);

        context.setAttribute("projectGid", projectGid);  // Testng yontemi degerleri baska methodlara almamizi sagliyor

        Dummy.setDummy("name", "sedat");
        Dummy.setDummy("number", 34343);


        response.prettyPrint();
    }

    @Test(priority = 2)
    public void createProjectRandom() {
        System.out.println("Working 2");

        Data data = Data.randomProject();

        //  project.setName("ensar"); // Eger  random degeleri degistirmek istiyorsak tekrar set edebeiliriz.

        MainData mainData = new MainData();
        mainData.setData(data);


        response = com.Asana.endPoints.Project.create_A_Project(mainData);


        response.prettyPrint();
    }


    @Test(priority = 3)
    public void createProjectBuild() {
        System.out.println("Working 3");

        Data data = Data.randomProject();

        data.setName("ensar")
                .setColor("light-pink")
                .setTeam("1205512931288990")
                //  .setMoney(0)
                .setWorkspace("1205485861998849");


//        Data data1=Data.emptyProject()
//                .setName("2. Yontem")              // "2. yontem
//                .setWorkspace("1205485861998849")
//                .setTeam("1205512931288990");


        MainData mainData = new MainData();
        mainData.setData(data);


        response = com.Asana.endPoints.Project.create_A_Project(mainData);


        response.prettyPrint();


    }


    @Test(priority = 4)
    public void deleteProject(ITestContext context) {
        System.out.println("Working 4");

        String id = context.getAttribute("projectGid").toString();
        System.out.println("id = " + id);
        response = Project.delete_A_Project(id);

        String nameOnemsiz = Dummy.getDummy("name");
        int numberOnemsiz = Dummy.getDummy("number");


        System.out.println("numberOnemsiz = " + numberOnemsiz);
        System.out.println("nameOnemsiz = " + nameOnemsiz);

    }

}
