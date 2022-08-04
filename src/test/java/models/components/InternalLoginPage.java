package models.components;

import org.openqa.selenium.WebDriver;

public class InternalLoginPage extends LoginPage{

    public InternalLoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String username() {
        return "InternalLoginPage | username";
    }

    @Override
    public String password() {
        return "InternalLoginPage | password";
    }

    @Override
    public String loginBtn() {
        return "InternalLoginPage | loginBtn";
    }
}
