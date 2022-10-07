package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementDisplaying {

    public static void main(String[] args) {

        //get chrome session
        WebDriver driver = DriverFactory.getChormeDriver();

            driver.get("https://the-internet.herokuapp.com/login");
            List <WebElement> elementList=driver.findElements(By.tagName("taolao"));
            if (!elementList.isEmpty())
                //Assert.fail("reason..")

        //quit
        driver.quit();

    }
}
