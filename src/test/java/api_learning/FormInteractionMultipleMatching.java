package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormInteractionMultipleMatching {

    public static void main(String[] args) {

        //get chrome session
        WebDriver driver = DriverFactory.getChormeDriver();

        try {

            //navigte to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            //define selector values
            By loginInputField = By.tagName("input");

            //Iinteraction
            List<WebElement> loginFormFieldsElm = driver.findElements(loginInputField);
            final int USERNAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;
            loginFormFieldsElm.get(USERNAME_INDEX).sendKeys("teo@gmail.co");
            loginFormFieldsElm.get(PASSWORD_INDEX).sendKeys("12345");

            //Debug
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();

        }

        //quit
        driver.quit();

    }
}
