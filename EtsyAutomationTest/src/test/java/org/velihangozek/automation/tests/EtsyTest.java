package org.velihangozek.automation.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.velihangozek.automation.pages.HomePage;
import org.velihangozek.automation.pages.LoginPage;
import org.velihangozek.automation.utils.ConfigReader;
import org.velihangozek.automation.utils.DriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EtsyTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @Override
    @BeforeEach
    public void setupTest() {
        super.setupTest(); // Call the BaseTest setup logic
        System.out.println("EtsyTest-specific setup logic...");
        // Initialize the test instance for Extent Reports
        test = extent.createTest("EtsyTest: Test - " + getClass().getSimpleName());

        // Initialize the page objects
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Test
    @DisplayName("Test Login Functionality")
    public void testLogin() {
        test.info("Starting Login Test"); // Log step to Extent Reports
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));4
        String pageTitle = DriverManager.getDriver().getTitle();
        test.info("Page title after login: " + pageTitle);  // Log additional details
        assertTrue(pageTitle.contains("Etsy"), "Login failed: Etsy title does not match.");
        test.pass("Login Test Passed");  // Mark test as passed in Extent Reports
    }

    @Test
    @DisplayName("Test Product Search")
    public void testSearchProduct() {
        String productName = "handmade necklace";
        test.info("Searching for product: " + productName);
        homePage.searchProduct(productName);

        boolean isProductFound = DriverManager.getDriver().getPageSource().contains("handmade");
        test.info("Search result contains 'handmade': " + isProductFound);

        assertTrue(isProductFound, "Search failed: 'handmade' keyword not found in the page source.");
        test.pass("Product Search Test Passed");

    }
}