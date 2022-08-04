package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMod02;
import org.openqa.selenium.WebDriver;

public class LoginTestMod02 {

    public static void main(String[] args) {

        //get chrome session
        WebDriver driver = DriverFactory.getChormeDriver();

        try {

            //navigte to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            LoginPageMod02 loginPageMod02 = new LoginPageMod02(driver);
            loginPageMod02.inputUsername("oanh@st.com");
            loginPageMod02.inputPassword("12345678");
            loginPageMod02.clickloginBtn();


        }catch (Exception e){
            e.printStackTrace();

        }

        //quit
        driver.quit();

    }
}
