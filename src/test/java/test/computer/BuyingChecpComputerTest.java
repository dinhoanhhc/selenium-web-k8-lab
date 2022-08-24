package test.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingChecpComputerTest extends BaseTest implements Urls {

    @Test
    public void testCheapComputerBuying() {
        driver.get(demoBaseUrl.concat("build-your-cheap-computer"));
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver,CheapComputerComponent.class);

        orderComputerFlow.buildCompSpecAndAddToCart();
    }
}
