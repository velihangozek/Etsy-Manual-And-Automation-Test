package org.velihangozek.automation.pages;

import org.velihangozek.automation.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    public HomePage() {
        this.driver = DriverManager.getDriver();
    }

    private By searchBox = By.id("global-enhancements-search-query");
    private By searchButton = By.xpath("//button[@type='submit']");

    public void searchProduct(String productName) {
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }
}
