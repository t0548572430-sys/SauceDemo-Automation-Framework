package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;

public class UserFlowTests extends BaseTest {

    @Test(description = "Edge Case: Verifying that the cart is saved (Persistence) after Logout and Re-login")
    public void testCartPersistenceAfterLogout() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        System.out.println("Step 1: Login and add item to cart");
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();

        String countBefore = inventoryPage.getCartBadgeCount();
        Assert.assertEquals(countBefore, "1", "The product was not initially added to the cart.");

        System.out.println("Step 2: Performing Logout");
        inventoryPage.logout();

        System.out.println("Step 3: Logging back in with the same user");
        loginPage.login("standard_user", "secret_sauce");

        System.out.println("Step 4: Validating cart is still populated");
        String countAfter = inventoryPage.getCartBadgeCount();

        Assert.assertEquals(countAfter, "1", "Bug: Cart emptied after Logout!");
    }
}