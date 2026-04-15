package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class PurchaseTests extends BaseTest {

    @Test
    public void fullPurchaseFlow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutStepOnePage stepOne = new CheckoutStepOnePage(driver);
        stepOne.fillInformation("Israel", "Israeli", "12345");

        CheckoutStepTwoPage stepTwo = new CheckoutStepTwoPage(driver);
        stepTwo.clickFinish();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("checkout-complete"));

        String successMessage = stepTwo.getConfirmationMessage();
        Assert.assertTrue(successMessage.toLowerCase().contains("thank you"), "The order was not completed!");
    }

}