package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By checkoutButton = By.id("checkout");
    private By cartItems = By.className("cart_item");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public int getCartItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean isCartEmpty() {
        return getCartItemsCount() == 0;
    }
}