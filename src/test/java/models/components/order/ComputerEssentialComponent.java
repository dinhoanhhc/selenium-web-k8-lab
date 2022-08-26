package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ComputerEssentialComponent extends Component {

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public abstract String selectProcessorType(String type);
    public abstract String selectRAMType(String type);

    public String selectHDD(String type){
        return selectComOption(type);
    }

    public String selectOS(String type){
        return selectComOption(type);
    }

    protected String selectComOption(String type){
        String selectorStr = "//label[contains(text(),\"" + type+ "\")]";
        By optionSel = By.xpath(selectorStr);
        WebElement optionElem = null;

        try {
            optionElem = component.findElement(optionSel);
        }catch (Exception ignored){}

        if (optionElem !=null){
            optionElem.click();
            return optionElem.getText();

        }else {
            throw new RuntimeException("The option " + type +"s not existing to select");
        }
    }
}
