package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DocumentPage {

    WebDriver driver;
    String documentURL = "https://app.levelset.com/wizard/SelectDocument/?_ga=2.181785636.2036325315.1710678028-1601854521.1710678027";
    String priceXpath = "//div[@class='section']/div[4]/div/div[2]/span[@class='price-amount']";
    String documentXpath ="//div[@class='section']/div[4]";

    public DocumentPage(WebDriver driver){
        this.driver=driver;
    }
    public void navigateToDocument(){
        driver.get(documentURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(documentXpath)));
    }
    public  String getPrice(){
        WebElement priceElement = driver.findElement(By.xpath(priceXpath));
        String price = priceElement.getText();
        return price;
    }
}
