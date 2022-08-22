package test;

import driver.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeTest
    public void initBrowserSession(){
        driver= DriverFactory.getChormeDriver();
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowserSession(){
        if (driver != null) driver.quit();
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE){
            //tstMethodName-yyyy-mm-dd-hr-mm-sec.pgn

            //1. get method name
            String methodName = result.getName();

            //2. Get Taken time
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) +1 ;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String filename = methodName +"-" +y +"-" +m +"-"+d +"-"+hr +"-"+ min +"-" +sec+".png";

            //3. Take Screenshot
           File sctreenshotBase64Data = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

           try {
               //4. Save
               String fileLocation = System.getProperty("user.dir") + "/screenshots/" +filename;
               FileUtils.copyFile(sctreenshotBase64Data, new File(fileLocation));

               //5. attack to allure reporter
               Path content = Paths.get(fileLocation);
               try (InputStream inputStream = Files.newInputStream(content)) {
                   Allure.addAttachment(methodName, inputStream);
               }

           }catch (Exception e){
               e.printStackTrace();
           }

        }

    }

}