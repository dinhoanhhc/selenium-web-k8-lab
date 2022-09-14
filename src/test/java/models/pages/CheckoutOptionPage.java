package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOptionPage extends BasePage {
    public static final By checkoutAsGuestBtn =  By.cssSelector(".checkout-as-guest-button");

    public CheckoutOptionPage(WebDriver driver) {
        super(driver);
    }

    public void checkoutAsGuest(){
        component.findElement(checkoutAsGuestBtn).click();
    }
}
