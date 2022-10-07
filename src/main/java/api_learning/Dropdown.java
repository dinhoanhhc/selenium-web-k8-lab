package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import suport.ui.SelectEx;
import url.Urls;

public class Dropdown implements Urls {

    public static void main(String[] args) {

        //get a chorme session
        WebDriver driver = DriverFactory.getChormeDriver();

        try{
            //navitage to target base
            driver.get(baseUrl.concat(dropdownSlug));

            //Dropdown loccator & element
            By dropdownSel=By.id("dropdown");
            WebElement dropdownElm = driver.findElement(dropdownSel);

            //Select dropdown
            SelectEx select = new SelectEx(dropdownElm);

            //Select by visible text Option 1
            select.selectOption1();

            Thread.sleep(1000);

            //Select by index option 2
            select.selectByIndex(2);
            Thread.sleep(1000);

            //Select by vaue Option 1
            select.selectByValue("1");
            Thread.sleep(1000);



        }
        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
