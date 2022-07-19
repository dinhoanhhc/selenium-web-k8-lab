package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkTestInteration {

    public static void main(String[] args) {

        //get chrome session
        WebDriver driver = DriverFactory.getChormeDriver();

        try {

            //navigte to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            //define selector values
            //By linkTextSel = By.linkText("Elemental Selenium");
            //
            By linkTextSel = By.partialLinkText("Elemental Selenium");


            //find elements
            WebElement linkTextElem = driver.findElement(linkTextSel);


            //interaction
            linkTextElem.click();

            //Debug
            Thread.sleep(15000);
        }catch (Exception e){
            e.printStackTrace();

        }

        //quit
        driver.quit();

    }
}
