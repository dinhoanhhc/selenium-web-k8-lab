package test_flows.computer;

import models.components.cart.TotalComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.computer.ComputerData;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent>{

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;
    private final int  quantity;
    private double  totalItemPrice;


    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = 1;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildCompSpecAndAddToCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);

        //Unselect all default options
        computerEssentialComp.unselectDefaultOptions();

        String processorFullStr= computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAdditionalPrice = extactAdditionalPrice(processorFullStr);
        String ramFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAdditionalPrice = extactAdditionalPrice(ramFullStr);
        String hddFullStr = computerEssentialComp.selectHDD(computerData.getHdd());
        double additionalHddPrice = extactAdditionalPrice(hddFullStr);


        double additionalOsPrice = 0;
        if (computerData.getOs() !=null){
           String fullOsStr = computerEssentialComp.selectOS(computerData.getOs());
            additionalOsPrice = extactAdditionalPrice(fullOsStr);
        }
        System.out.println("additionalOsPrice: "+additionalOsPrice);

        String fullSoftwareStr = computerEssentialComp.selectSoftware(computerData.getSoftware());
        double additionalSoftwarePrice = extactAdditionalPrice(fullSoftwareStr);

        //Calculate item's price and add to cart
        double basePrice = computerEssentialComp.productPrice();
        double allAdditioalPrices =
                processorAdditionalPrice + ramAdditionalPrice + additionalHddPrice + additionalOsPrice + additionalSoftwarePrice;
        totalItemPrice = (basePrice + allAdditioalPrices) * quantity;

        //And add to cart
        computerEssentialComp.clickOnAddToCrtBtn();
        computerEssentialComp.waitUntilItemAddedToCart();

        // Then Navigate to Shoppng cart
        computerItemDetailsPage.headerComp().clickShoppingCartLink();
        try{
            Thread.sleep(3000);
        }catch (Exception ignore){}

    }

    private double extactAdditionalPrice(String itemStr){
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");//lay data trong dau []
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()){
            price = Double.parseDouble(matcher.group(1).replaceAll("[-+]", ""));
        }
        return price;
    }

    public void verifyShoppingCartPage(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalComp = shoppingCartPage.totalComp();
        Map<String, Double> priceCategories = totalComp.priceCategories();
        for (String priceType : priceCategories.keySet()) {
            System.out.println(priceType+":"+priceCategories.get(priceType));

        }
    }

}
