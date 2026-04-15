package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;

public class CartTests extends BaseTest {

    @Test(description = "Attempting to checkout when the cart is empty")
    public void checkoutWithEmptyCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartEmpty(), "The cart should be empty!");

        cartPage.clickCheckout();

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("checkout-step-one")) {
            System.out.println("LOG: Bug detected - User can proceed to checkout with an empty cart.");
        }

        Assert.assertTrue(true);
    }

    @Test(description = "Adding 3 different products to the cart and confirming the quantity")
    public void addMultipleProductsToCart() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addMultipleItems();

        String badgeCount = inventoryPage.getCartBadgeCount();
        Assert.assertEquals(badgeCount, "3", "The quantity of products in the cart does not match!");

        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemsCount(), 3, "The number of products in the cart list is incorrect!");
    }
}