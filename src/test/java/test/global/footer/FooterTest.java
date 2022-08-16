package test.global.footer;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTest {

    @Test()
    public void testFooterCategoryPage() {
        WebDriver driver = DriverFactory.getChormeDriver();
        try {
            driver.get(Urls.demoBaseUrl);
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyProductCatFooterComponent();


        }catch (Exception e){
            e.printStackTrace();
        } finally {
            driver.quit();

        }

    }

    @Test()
    public void testFooterRegisterPage() {

    }

    @Test()
    public void testFooterLoginPage() {

    }

    @Test()
    public void testFooterHomePage() {

    }


}
