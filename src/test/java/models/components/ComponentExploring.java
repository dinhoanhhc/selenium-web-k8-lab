package models.components;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.sql.Driver;

public class ComponentExploring {

    public <T extends LoginPage> void login(Class<T>  loginPageClass){

        Class<?>[] parameters = new Class[]{WebDriver.class};

        try
        {
            Constructor<T> constructor = loginPageClass.getConstructor(parameters);
            T loginPageObj = constructor.newInstance(DriverFactory.getChormeDriver());
            loginPageObj.login();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ComponentExploring().login(InternalLoginPage.class);
        new ComponentExploring().login(ExternalLoginPage.class);
    }
}
