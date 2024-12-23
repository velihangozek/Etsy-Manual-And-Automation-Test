package org.velihangozek.automation.pages;

import org.velihangozek.automation.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.velihangozek.automation.utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;

    public LoginPage() {
        this.driver = DriverManager.getDriver();
    }

    private By signInButton = By.xpath("//button[contains(text(),'Sign in')]");
    private By emailField = By.id("join_neu_email_field");
    private By passwordField = By.id("join_neu_password_field");
    private By submitButton = By.name("submit_attempt");

    public void login(String email, String password) {
        WaitUtils.clickWhenReady(driver, signInButton);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
    }
}