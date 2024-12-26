package org.velihangozek.automation.pages;

import org.velihangozek.automation.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    private WebDriver driver;

    // Locators for UI elements
    private By addToCartButton = By.cssSelector(".btn-buy-box");
    private By proceedToCheckoutButton = By.cssSelector(".proceed-to-checkout");

    // Constructor to initialize the driver
    public ProductPage() {
        this.driver = DriverManager.getDriver();
    }

    // Page methods
    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void proceedToCheckout() {
        driver.findElement(proceedToCheckoutButton).click();
    }
}
