package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormInteraction {

    public static void main(String[] args) {

        //get chrome session
        WebDriver driver = DriverFactory.getChormeDriver();

        try {

            //navigte to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            //define selector values
            By usernameSel = By.id("username");
            By passwordSel = By.cssSelector("#password");
            By loginBtnSel = By.cssSelector("[type='submit']");

            //find elements
            WebElement usernameElem = driver.findElement(usernameSel);
            WebElement passwordElem = driver.findElement(passwordSel);
            WebElement loginBtnElem = driver.findElement(loginBtnSel);

            //Get attribute value
            System.out.println("Login button type: "+loginBtnElem.getAttribute("type"));
            System.out.println("Login button background color: "+loginBtnElem.getCssValue("background-color"));

            //interaction
            usernameElem.clear();
            usernameElem.sendKeys("tomsmith");
            passwordElem.sendKeys("SuperSecretPassword!");
            loginBtnElem.click();

            //Go back to pre page
            driver.navigate().back();

            //Refesh
            driver.navigate().refresh();

            //re interact with the element again
            usernameElem = driver.findElement(usernameSel);
            passwordElem = driver.findElement(passwordSel);
            loginBtnElem = driver.findElement(loginBtnSel);
            usernameElem.sendKeys("tesstusername");
            passwordElem.sendKeys("tesstusername");
            loginBtnElem.click();


            //Debug
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();

        }

        //quit
        driver.quit();

    }
}
