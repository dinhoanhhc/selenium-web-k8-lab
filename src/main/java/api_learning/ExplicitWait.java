package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import suport.ui.WaitMoreThanOneTab;
import url.Urls;

import java.time.Duration;

public class ExplicitWait {
    public static void main(String[] args) {

        //get a chorme session
        WebDriver driver = DriverFactory.getChormeDriver();

        try{
            //navitage to target base
            driver.get(Urls.baseUrl.concat(Urls.loginSlug));

            //Dropdown loccator & element
            By testSel = By.cssSelector("#teo");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(testSel));
            //wait.until(ExpectedConditions.visibilityOf(driver.findElement(testSel)));
            wait.until(new WaitMoreThanOneTab());

            //wndows/tabs ---> waitUnitl tabs >1




        }
        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
