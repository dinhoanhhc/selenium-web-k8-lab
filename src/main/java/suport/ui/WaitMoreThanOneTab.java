package suport.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitMoreThanOneTab implements ExpectedCondition <Boolean>{

    @Override
    public Boolean apply(WebDriver driver) {
        return driver.getWindowHandles().size() >1;
    }

    @Override
    public String toString() {
        return "tab number to be bugger than 2!";
    }
}
