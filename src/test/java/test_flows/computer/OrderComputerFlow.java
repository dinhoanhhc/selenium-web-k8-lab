package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.checkout.ShippingMethodComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckOutPage;
import models.pages.CheckoutOptionPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.computer.ComputerData;
import test_data.computer.DataObjectBuilder;
import test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent>{

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;
    private final int  quantity;
    private double  totalItemPrice;
    private UserDataObject defaultCheckoutUser;


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
        List<CartItemRowComponent> cartItemRowComps = shoppingCartPage.cartItemRowComponents();
        if (cartItemRowComps.isEmpty()){
            Assert.fail("[ERROR] there no item displayed in the shoppping cart");
        }

        double currentSubtotal = 0;
        double currentTotalUnitPrices = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemRowComps) {
            currentSubtotal = currentSubtotal + cartItemRowComp.subTotal();
            currentTotalUnitPrices = currentTotalUnitPrices + (cartItemRowComp.unitPrice() * cartItemRowComp.quantity());
            
        }

        Assert.assertEquals(currentSubtotal, currentTotalUnitPrices,
                "[ERR] Shopping cart's sub -total is incorrect");

        TotalComponent totalComp = shoppingCartPage.totalComp();
        Map<String, Double> priceCategories = totalComp.priceCategories();
        double checkoutSubTotal =  0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal =0;
        for (String priceType : priceCategories.keySet()) {
            double priceValue = priceCategories.get(priceType);
            if (priceType.startsWith("Sub-Total")){
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")){
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal = checkoutOtherFeesTotal + priceValue;
            }

        }

        Assert.assertEquals(checkoutSubTotal,currentSubtotal,"[ERR ] Subtotal is incorrect");
        Assert.assertEquals(checkoutTotal,checkoutSubTotal+checkoutOtherFeesTotal,"[ERR ] checkoutTotal is incorrect");
    }

    public void agreeTOSAndCheckout(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComp().agreeTOS();
        shoppingCartPage.totalComp().clickOnCheckoutButton();
        new CheckoutOptionPage(driver).checkoutAsGuest();
    }

    public void inputBillingAddress(){
        String defaultCheckoutUserJSONLoc = "/src/test/java/test_data/DefaultCheckoutUser.json";
        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJSONLoc,UserDataObject.class);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        BillingAddressComponent billingAddressComp = checkOutPage.billingAddressComp();
        billingAddressComp.selectInputNewAddress();
        billingAddressComp.inputFirstname(defaultCheckoutUser.getFirstName());
        billingAddressComp.inputLastname(defaultCheckoutUser.getLastName());
        billingAddressComp.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComp.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComp.selectState(defaultCheckoutUser.getState());
        billingAddressComp.inputCity(defaultCheckoutUser.getCity());
        billingAddressComp.inputAdd1(defaultCheckoutUser.getAdd1());
        billingAddressComp.inputZIPCode(defaultCheckoutUser.getZipCode());
        billingAddressComp.inputPhoneNo(defaultCheckoutUser.getPhoneNum());
        billingAddressComp.clickOnContinueBtn();

    }

    public void inputShippingAddress(){
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.shippingAddressComponent().clickOnContunueBtn();
    }

    public void selectPaymentMethod(){
        List<String> shippingMethods = Arrays.asList("Ground","Next Day Air","2nd Day Air");
        String randomShippingMethod = shippingMethods.get(new SecureRandom().nextInt(shippingMethods.size()));
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        ShippingMethodComponent shippingMethodComp = checkOutPage.shippingMethodComp();
        shippingMethodComp.selectShippingMethod(randomShippingMethod);
        shippingMethodComp.clickOnContinueButton();

        try {
            Thread.sleep(3000);
        }catch (Exception ignored){};

    }

    public void inputPaymentInfo(){
        Assert.fail();
    }

    public void confirmOrder(){
        Assert.fail();
    }

}
