package models.components;

import org.openqa.selenium.WebDriver;

public abstract class LoginPage {

    protected WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(){
        System.out.println(username());
        System.out.println(password());
        System.out.println(loginBtn());
    }

    public void verifyLoginSuccess(){
        System.out.println(("Verify Dashboard display"));
    }

    public abstract String username();
    public abstract String password();
    public abstract String loginBtn();
}
