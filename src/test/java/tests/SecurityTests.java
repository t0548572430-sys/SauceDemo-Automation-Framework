package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import pages.CheckoutStepOnePage;

public class SecurityTests extends BaseTest {

    @Test(description = "Edge Case: Session Lost in the Middle of Checkout (Cookies Deleted)")
    public void testCheckoutSessionTermination() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutStepOnePage stepOne = new CheckoutStepOnePage(driver);

        System.out.println("Step 1: Logging in and adding items");
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();
        cartPage.clickCheckout();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"), "We did not reach the checkout page.");
        System.out.println("Step 2: Terminating Session (Deleting all cookies)");

        driver.manage().deleteAllCookies();
        System.out.println("Step 3: Attempting to continue purchase without Session");

        stepOne.fillInformation("Hacker", "User", "00000");
        System.out.println("Step 4: Validating Security Enforcement");

        String currentUrl = driver.getCurrentUrl();
        boolean isLoginPageVisible = loginPage.isElementPresent(By.id("login-button"));

        Assert.assertTrue(currentUrl.contains("index.html") || isLoginPageVisible,
                "Security breach! The system allowed the checkout process to continue even though the session was deleted.");
    }
}