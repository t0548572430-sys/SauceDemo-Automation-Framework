package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepTwoPage extends BasePage {

    private By finishButton = By.xpath("//button[@id='finish' or @name='finish']");
    private By completeHeader = By.className("complete-header");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinish() {
        clickWithJS(finishButton);
    }

    public String getConfirmationMessage() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
        return header.getText();
    }
}