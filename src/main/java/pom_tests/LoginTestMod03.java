package pom_tests;

import driver.DriverFactory;
import models.components.LoginFormComponent;
import models.pages.LoginPageMod03;
import org.openqa.selenium.WebDriver;

public class LoginTestMod03 {

    public static void main(String[] args) {

        //get chrome session
        WebDriver driver = DriverFactory.getChormeDriver();

        try {

            //navigte to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            LoginPageMod03 loginPage = new LoginPageMod03(driver);
            LoginFormComponent loginFormComp =loginPage.loginFormComp();
            loginFormComp.inputUsername("oanh@st.com");
            loginFormComp.inputPassword("12345678");
            loginFormComp.clickloginBtn();


        }catch (Exception e){
            e.printStackTrace();

        }

        //quit
        driver.quit();

    }
}
