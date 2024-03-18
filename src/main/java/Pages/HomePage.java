package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage {

    WebDriver driver;
    String homeURL="https://www.levelset.com/";
    String buttonGetPaidXpath = "//*[@id=\"header\"]/div/div/div[2]/nav/ul/li[8]/a";
    String documentsSelectionXpath = "//ul[@class='d-flex']/li/a";
    String documentXpath ="//div[@class='section']/div[4]";

    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    public void navigateToHome(){
        driver.get(homeURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buttonGetPaidXpath)));
    }
    public DocumentPage selectGetPaid() {
        WebElement buttonGetPaid = driver.findElement(By.xpath(buttonGetPaidXpath));

        if (buttonGetPaid.isEnabled()) {
            buttonGetPaid.click();
            WebElement documentsSelection = driver.findElement(By.xpath(documentsSelectionXpath));

            if (documentsSelection.isEnabled()) {
                documentsSelection.click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                int maxTrials = 3;
                int count = 0;
                boolean documentPageLoaded = false;

                while (count < maxTrials && !documentPageLoaded) {
                    try {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(documentXpath)));
                        documentPageLoaded = true;
                    } catch (TimeoutException e) {
                        navigateToHome();
                        buttonGetPaid.click();
                        documentsSelection.click();
                        count++;
                    }
                }

                if (!documentPageLoaded) {
                    System.out.println("Error: The document page did not load.");
                }
            } else {
                System.out.println("Error: The documents selection button is not clickable.");
            }
        } else {
            System.out.println("Error: The Get Paid button is not clickable.");
        }

        return new DocumentPage(driver);
    }
}