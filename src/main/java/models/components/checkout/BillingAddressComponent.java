package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@ComponentCssSelector("#opc-billing")
public class BillingAddressComponent extends Component {

    private final static By inputAddresDropdowmnSel = By.id("billing-address-select");
    private final static By firstNameSel = By.id("BillingNewAddress_FirstName");
    private final static By lastNameSel = By.id("BillingNewAddress_LastName");
    private final static By emailSel = By.id("BillingNewAddress_Email");
    private final static By selectCountryDropdownSel = By.id("BillingNewAddress_CountryId");
    private final static By selectStateDropdownSel = By.id("BillingNewAddress_StateProvinceId");
    private final static By loadingStateProgressBarSel = By.id("states-loading-progress");
    private final static By citySel = By.id("BillingNewAddress_City");
    private final static By add1Sel = By.id("BillingNewAddress_Address1");
    private final static By zipCodeSel = By.id("BillingNewAddress_ZipPostalCode");
    private final static By phoneNoSel = By.id("BillingNewAddress_PhoneNumber");
    private final static By continueBtnSel = By.cssSelector(".new-address-next-step-button");

    public BillingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectInputNewAddress(){
        if (!component.findElements(inputAddresDropdowmnSel).isEmpty()){
            Select select = new Select(component.findElement(inputAddresDropdowmnSel));
            select.selectByVisibleText("New Address");
        }
    }

    public void inputFirstname(String firstname){
        component.findElement(firstNameSel).sendKeys(firstname);
    }

    public void inputLastname(String lastname){
        component.findElement(lastNameSel).sendKeys(lastname);
    }

    public void inputEmail(String email){
        component.findElement(emailSel).sendKeys(email);
    }

    public void selectCountry(String coutry){
        Select select = new Select(component.findElement(selectCountryDropdownSel));
        select.selectByVisibleText(coutry);
        wait.until(ExpectedConditions.invisibilityOf(component.findElement(loadingStateProgressBarSel)));
    }

    public void selectState(String state){
        Select select = new Select(component.findElement(selectStateDropdownSel));
        select.selectByVisibleText(state);
    }

    public void inputCity(String city){
        component.findElement(citySel).sendKeys(city);
    }

    public void inputAdd1(String add1){
        component.findElement(add1Sel).sendKeys(add1);
    }

    public void inputZIPCode(String zipcode){
        component.findElement(zipCodeSel).sendKeys(zipcode);
    }

    public void inputPhoneNo(String phoneNo){
        component.findElement(phoneNoSel).sendKeys(phoneNo);
    }

    public void clickOnContinueBtn(){
        WebElement continueBtn = component.findElement(continueBtnSel);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
    }
}
