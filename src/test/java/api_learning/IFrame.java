package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class IFrame implements Urls {

    public static void main(String[] args) {

        //get a chorme session
        WebDriver driver = DriverFactory.getChormeDriver();

        try{
            //navitage to target base
            driver.get(baseUrl.concat(iframeSlug));

            //Locate the iframe
            By iFrameSel = By.cssSelector("[id$='ifr']");
            WebElement iFrameElem = driver.findElement(iFrameSel);

            //Switch to the ifram
            driver.switchTo().frame(iFrameElem);

            //Locate element inside the iframe
            WebElement editorInputElm = driver.findElement(By.id("tinymce"));
            editorInputElm.click();
            editorInputElm.clear();
            editorInputElm.sendKeys("This iss new text,...");
            Thread.sleep(1000);

            //Switch bact patent frame
            driver.switchTo().defaultContent();
            driver.findElement(By.linkText("Elemental Selenium")).click();





        }
        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
