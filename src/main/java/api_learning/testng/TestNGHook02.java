package api_learning.testng;

import org.testng.annotations.*;

public class TestNGHook02 {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("\t --->beforeTest");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("\t\t --->beforeClass");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("\t\t\t --->beforeMethod");
    }

    @Test
    public void testSth(){
        System.out.println("\t\t\t\t --->TEST");
    }

    @Test
    public void testSthElse(){
        System.out.println("\t\t\t\t --->TEST STH ELSE");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("\t --->afterTest");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("\t\t --->afterClass");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("\t\t\t --->afterMethod");
    }




}
