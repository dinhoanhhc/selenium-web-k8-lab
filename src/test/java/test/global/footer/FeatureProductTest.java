package test.global.footer;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.components.product.ProductItemComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import url.Urls;

import java.util.List;

public class FeatureProductTest {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChormeDriver();

        try {
            testFeatureProductHomePage(driver);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void testFeatureProductHomePage(WebDriver driver) {
        driver.get(Urls.demoBaseUrl);
        HomePage homePage = new HomePage(driver);
        List<ProductItemComponent>productItemComps =
                homePage.productGridComp().productItemComps();

        productItemComps.forEach(productItemComp ->{
            System.out.println(productItemComp.productTitleElm().getText());
        });

    }

}
