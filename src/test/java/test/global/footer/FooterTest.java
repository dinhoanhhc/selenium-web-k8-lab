package test.global.footer;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import suport.verification.Verifier;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTest {

    @Test(priority = 1)
    public void testFooterHomePage() {
        WebDriver driver = DriverFactory.getChormeDriver();
        driver.get(Urls.demoBaseUrl);
        try {
            HomePage homePage = new HomePage(driver);
            InformationColumnComponent informationColumnComponent =
                    homePage.footerComp().informationColumnComp();

            CustomerServiceColumnComponent customerServiceColumnComponent =
                    homePage.footerComp().customerServiceColumnComp();

            testFooterColumn(informationColumnComponent);
            testFooterColumn(customerServiceColumnComponent);
        }catch (Exception ignored){}

        driver.quit();
    }

    @Test(priority = 2)
    public void testFooterCategoryPage() {
        String actualResult = "Oanh";
        String expectedResult = "Dinh";
//        Verifier.verifyEquals(actualResult,expectedResult);
        //Hard assertion
        Assert.assertEquals(actualResult, expectedResult,"[ERR] Welcome message is incorrect");
        Assert.assertTrue(actualResult.equals(expectedResult),"..");
        Assert.assertFalse(actualResult.equals(expectedResult),"..");
        Assert.fail("..");
    }

    @Test(priority = 3)
    public void testFooterRegisterPage() {
        WebDriver driver = DriverFactory.getChormeDriver();
        driver.get(Urls.demoBaseUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test(priority = 4)
    public void testFooterLoginPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1,2);
        softAssert.assertEquals(true,true);
        softAssert.assertEquals(2,3);


        softAssert.assertAll();
        System.out.println("hello");
    }

    private static void testFooterColumn(FooterColumnComponent footerColumnComponent){
        System.out.println(footerColumnComponent.headerElm().getText());
        footerColumnComponent.linksElm().forEach(link ->{
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });
    }
}
