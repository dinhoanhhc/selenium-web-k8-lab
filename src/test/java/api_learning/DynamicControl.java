package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import suport.ui.SelectEx;
import suport.ui.WaitForElementEnable;
import url.Urls;

import java.time.Duration;

public class DynamicControl implements Urls {
    public static void main(String[] args) {

        //get a chorme session
        WebDriver driver = DriverFactory.getChormeDriver();

        try{
            //navitage to target base
            driver.get(baseUrl.concat(dynamicControlSlug));

            //Define parent locators | 2 forms| checkbox form + input form
            By checkboxFormSel =By.id("checkbox");
            By inputFormSel=By.id("input-example");

            //Checkbox form interation
            WebElement checkboxFormElm = driver.findElement(checkboxFormSel);
            WebElement checkboxElm = checkboxFormElm.findElement(By.tagName("input"));
            if (!checkboxElm.isSelected()) checkboxElm.click();

            //Inpur form iteraction
            WebElement inputFormElm = driver.findElement(inputFormSel);
            WebElement inputFieldElm = inputFormElm.findElement(By.tagName("input"));
            WebElement inputBtnElm = inputFormElm.findElement(By.tagName("button"));
            if (!inputFieldElm.isEnabled()) inputBtnElm.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
            wait.until(new WaitForElementEnable(By.cssSelector("#input-example input")));
            inputFieldElm.sendKeys("My name is Oanh, Hello!!!");







            //DEBUG purfpse only
            Thread.sleep(1000);


        }
        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }

}
