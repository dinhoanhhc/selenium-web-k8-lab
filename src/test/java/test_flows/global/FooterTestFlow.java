package test_flows.global;

import models.components.global.footer.FooterColumnComponent;
import org.openqa.selenium.WebDriver;

public class FooterTestFlow {

    private WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent(){
        verifyInformationColum();
        verifyCustomerService();
        verifyAccountColumn();
        verifyFollowUsColumn();


    }

    private void verifyInformationColum() {
    }

    private void verifyCustomerService() {
    }

    private void verifyAccountColumn() {
    }

    private void verifyFollowUsColumn() {
    }

    private static void testFooterColumn(FooterColumnComponent footerColumnComponent){
        System.out.println(footerColumnComponent.headerElm().getText());
        footerColumnComponent.linksElm().forEach(link ->{
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });
    }
}
