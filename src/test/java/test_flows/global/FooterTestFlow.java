package test_flows.global;

import models.components.global.TopMenuComponent;
import static models.components.global.TopMenuComponent.MainCatItem;
import static models.components.global.TopMenuComponent.CatItemComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.FooterComponent;
import models.pages.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import url.Urls;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {

    private WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent(){
        BasePage basePage=new BasePage(driver);
        FooterComponent footerComp = basePage.footerComp();
        verifyInformationColum(footerComp.informationColumnComp());
        verifyCustomerService(footerComp.customerServiceColumnComp());
//        verifyAccountColumn(footerComp.accountColumnComp());
//        verifyFollowUsColumn(footerComp.followUsColumnComp());


    }

    private void verifyInformationColum(FooterColumnComponent footerColumnComp) {
        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList( "Sitemap", "Shipping & Returns", "Privacy Notice", "Conditions of Use",
                "About us", "Contact us");
        List<String> expectedHrefs = Arrays.asList(baseUrl + "/sitemap", baseUrl + "/shipping-returns", baseUrl + "/privacy-policy", baseUrl + "/conditions-of-use",
                baseUrl + "/about-us", baseUrl + "/contactus");
        verifyFooterComponent(footerColumnComp,expectedLinkTexts,expectedHrefs);
    }

    private void verifyCustomerService(FooterColumnComponent footerColumnComp) {
        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts =  Arrays.asList(
                "Search", "News", "Blog", "Recently viewed products",
                "Compare products list", "New products");
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "/search",baseUrl +  "/news", baseUrl + "/blog",baseUrl +  "/recentlyviewedproducts",
                baseUrl + "/compareproducts", baseUrl + "/newproducts");
        verifyFooterComponent(footerColumnComp,expectedLinkTexts,expectedHrefs);
    }

    private void verifyAccountColumn(FooterColumnComponent footerColumnComp) {
        List<String> expectedLinkTexts = new ArrayList<>();
        List<String> expectedHrefs = new ArrayList<>();
        verifyFooterComponent(footerColumnComp,expectedLinkTexts,expectedHrefs);
    }

    private void verifyFollowUsColumn(FooterColumnComponent footerColumnComp) {
        List<String> expectedLinkTexts = new ArrayList<>();
        List<String> expectedHrefs = new ArrayList<>();
        verifyFooterComponent(footerColumnComp,expectedLinkTexts,expectedHrefs);
    }

    public void verifyProductCatFooterComponent(){
        // Random pickup  an item
        BasePage basePage = new BasePage(driver);
        TopMenuComponent topMenuComp = basePage.topMenuComp();
        List<MainCatItem> mainCatItems = topMenuComp.mainItemsElm();
        if (mainCatItems.isEmpty()) {
            Assert.fail("[ERR] There is no item on top menu !");
        }

        MainCatItem randomMainItemElm = mainCatItems.get(new SecureRandom().nextInt(mainCatItems.size()));
        String randomCatHref = randomMainItemElm.catItemLinkElem().getAttribute("href");

        // Get sublist
        List<CatItemComponent> catItemComps = randomMainItemElm.catItemComps();
        if (catItemComps.isEmpty()){
            randomMainItemElm.catItemLinkElem().click();
        } else {
            int randomIndex = new SecureRandom().nextInt(catItemComps.size());
            CatItemComponent randomCatItemComp = catItemComps.get(randomIndex);
            randomCatHref= randomCatItemComp.getComponent().getAttribute("href");
            randomCatItemComp.getComponent().click();
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlContains(randomCatHref));
        } catch (TimeoutException e){
             Assert.fail("[ERR] Target page is not matched !");
        }
        //Verifyy footer component
        verifyFooterComponent();




    }

    private static void verifyFooterComponent(
            FooterColumnComponent footerColumnComponent, List<String> expectedLinkTexts, List<String> expectedHrefs){

        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();

        for (WebElement link : footerColumnComponent.linksElm()) {
            actualLinkTexts.add(link.getText().trim());
            actualHrefs.add(link.getAttribute("href"));

        }
        if (actualLinkTexts.isEmpty() || actualHrefs.isEmpty()){
            Assert.fail("[ERR ] Texts or hyperlinks is impty in footer column !");
        }

        //Verify link text
        Assert.assertEquals(actualLinkTexts, expectedLinkTexts,"[ERR] Actal and expected link texts are different! ");

        //Verify Hrefs
        Assert.assertEquals(actualHrefs, expectedHrefs,"[ERR] Actal and expected hyper links are different! ");



    }
}
