package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import url.Urls;

import java.util.List;

public class MouseHoverAndNarrowDownSearching implements Urls {

    private final static By figureSel = By.className("figure");
    private final static By profileNameSel = By.cssSelector(".figcaption h5");
    private final static By profileLInkSel = By.cssSelector(".figcaption a");

    public static void main(String[] args) {

        //get a chorme session
        WebDriver driver = DriverFactory.getChormeDriver();

        try{
            //navitage to target base
            driver.get(baseUrl.concat(hoverSlug));

            //Target parent element
            List<WebElement> figuresElm=driver.findElements(figureSel);
            if (iframeSlug.isEmpty())
                throw new RuntimeException("There is no profile image displayed");

            //Define Action object
            Actions actions = new Actions(driver);
            for (WebElement figureElm:figuresElm){
                WebElement profileNameElm = figureElm.findElement(profileNameSel);
                WebElement profileLinkElm = figureElm.findElement(profileLInkSel);

                //BEFORE Mouse hover
                System.out.println(profileNameElm.getText()+"Is name "+profileNameElm.isDisplayed());
                System.out.println(profileNameElm.getText()+"Is link "+profileLinkElm.isDisplayed());

                // Mouse hover
                actions.moveToElement(figureElm).perform();

                //AFTER Mouse hover
                System.out.println(profileNameElm.getText()+"Is name "+profileNameElm.isDisplayed());
                System.out.println(profileNameElm.getText()+"Is link "+profileLinkElm.isDisplayed());


            }

            //Normal finding element
//            List<WebElement>figcaptionNames = driver.findElements(By.cssSelector(".figcaption h5"));
//            List<WebElement>figcaptionNames = driver.findElements(By.cssSelector(".figcaption ah5"));


            //Locate the iframe

        }
        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
