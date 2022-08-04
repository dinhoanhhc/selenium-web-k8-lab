package models.components;

import org.openqa.selenium.WebDriver;

public class ExternalLoginPage extends LoginPage{
    public ExternalLoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String username() {
        return "ExternalLoginPage | username";
    }

    @Override
    public String password() {
        return "ExternalLoginPage | password";
    }

    @Override
    public String loginBtn() {
        return "ExternalLoginPage | loginBtn";
    }
}
