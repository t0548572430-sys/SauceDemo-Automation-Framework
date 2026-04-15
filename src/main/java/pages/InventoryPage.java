package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {

    private By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By shoppingCartBadge = By.className("shopping_cart_badge");
    private By cartButton = By.id("shopping_cart_container");
    private By pageTitle = By.className("title");
    private By bikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private By boltTShirt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpack)).click();
    }

    public String getCartBadgeCount() {
        return driver.findElement(shoppingCartBadge).getText();
    }

    public void goToCart() {
        driver.findElement(cartButton).click();
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public void addMultipleItems() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpack)).click();
        driver.findElement(bikeLight).click();
        driver.findElement(boltTShirt).click();
    }

    public void logout() {
        driver.findElement(menuButton).click();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}