package api_learning;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LaunchBrowser {

    public static void main(String[] args) {
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation = "";
        if (OS.isFamilyMac()){
            chromeDriverLocation = currentProjectLocation + "/src/test/resources/drivers/chromedriver.exe";

        }

        if (OS.isFamilyWindows()){
            chromeDriverLocation = currentProjectLocation + "\\src\\test\\resources\\drivers\\chromedriver.exe.exe";

        }
        if (chromeDriverLocation.isEmpty()){
            throw new IllegalArgumentException("can not detect OS");}

            System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
            ChromeOptions chromeOptions = new ChromeOptions();

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com/");
        //DEBUG
        try {
            Thread.sleep(5000);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();


    }
}
