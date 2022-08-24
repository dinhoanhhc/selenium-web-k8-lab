package test_flows.computer;

import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import org.openqa.selenium.WebDriver;

public class OrderComputerFlow<T extends ComputerEssentialComponent>{

    private final WebDriver driver;
    private final Class<T> ComputerEssentialComponent;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent) {
        this.driver = driver;
        ComputerEssentialComponent = computerEssentialComponent;
    }

    public void buildCompSpecAndAddToCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(ComputerEssentialComponent);
        computerEssentialComp.selectProcessorType("2.2GHz");
        computerEssentialComp.selectRAMType("4GB");
        try {
            Thread.sleep(5000);
        }catch (Exception e){}
    }
}
