package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import url.Urls;

import java.time.Duration;
import java.util.List;

public class JsAlerts implements Urls {

    private final static By jsAlertSel = By.cssSelector("[onclick=\"jsAlert()\"]");
    private final static By jsAlertConfirmSel = By.cssSelector("[onclick=\"jsConfirm()\"]");
    private final static By jsAlertPromptSel = By.cssSelector("[onclick=\"jsPrompt()\"]");
    private final static By resultSel = By.cssSelector("#result");

    public static void main(String[] args) {

        //get a chorme session
        WebDriver driver = DriverFactory.getChormeDriver();

        try{
            //navitage to target base
            driver.get(baseUrl.concat(jsAlertSlug));
            WebElement triggerJsAlertBtnElm;
            Alert alert;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            //JS_ALERT
            triggerJsAlertBtnElm = driver.findElement(jsAlertSel);
            triggerJsAlertBtnElm.click();
            alert=wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert content: "+alert.getText());
            alert.accept();
            System.out.println("Result: "+driver.findElement(resultSel).getText());

            //JS_CONFIRM/Cancel
            triggerJsAlertBtnElm = driver.findElement(jsAlertPromptSel);
            triggerJsAlertBtnElm.click();
            alert=wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert content: "+alert.getText());
            alert.dismiss();
            System.out.println("Result: "+driver.findElement(resultSel).getText());

            //JS_PROMPT
            triggerJsAlertBtnElm = driver.findElement(jsAlertPromptSel);
            triggerJsAlertBtnElm.click();
            alert=wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert content: "+alert.getText());
            alert.sendKeys("My name is Oanh");
            alert.accept();
            System.out.println("Result: "+driver.findElement(resultSel).getText());


        }

            //Normal finding element
//            List<WebElement>figcaptionNames = driver.findElements(By.cssSelector(".figcaption h5"));
//            List<WebElement>figcaptionNames = driver.findElements(By.cssSelector(".figcaption ah5"));


            //Locate the iframe


        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
