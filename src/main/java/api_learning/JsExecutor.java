package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class JsExecutor implements Urls {
    public static void main(String[] args) {

        //get a chorme session
        WebDriver driver = DriverFactory.getChormeDriver();

        try{
            //Navigate to target base
            driver.get(baseUrl.concat(floatingMenuSlug));

            //Scroll to bottom
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);\n");

            //DEBUG ONLY
            Thread.sleep(2000);

            //Scroll to top
            javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight,0);\n");

            //DEBUG ONLY
            Thread.sleep(2000);

        }

        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
