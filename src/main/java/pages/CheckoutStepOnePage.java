package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepOnePage extends BasePage {

    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public void fillInformation(String fName, String lName, String zip) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(fName);
        driver.findElement(lastNameField).sendKeys(lName);
        driver.findElement(zipCodeField).sendKeys(zip);

        clickWithJS(continueButton);
    }
}