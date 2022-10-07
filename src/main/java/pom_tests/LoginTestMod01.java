package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMod01;
import org.openqa.selenium.WebDriver;

public class LoginTestMod01 {

    public static void main(String[] args) {

        //get chrome session
        WebDriver driver = DriverFactory.getChormeDriver();

        try {

            //navigte to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            LoginPageMod01 loginPageMod01 = new LoginPageMod01(driver);
            loginPageMod01.username().sendKeys("oanh@st.com");
            loginPageMod01.password().sendKeys("12345678");
            loginPageMod01.loginBtn();


        }catch (Exception e){
            e.printStackTrace();

        }

        //quit
        driver.quit();

    }
}
