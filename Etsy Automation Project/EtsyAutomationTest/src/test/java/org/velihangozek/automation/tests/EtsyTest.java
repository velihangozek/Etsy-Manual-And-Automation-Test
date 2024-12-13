package org.velihangozek.automation.tests;

import org.velihangozek.automation.pages.HomePage;
import org.velihangozek.automation.pages.LoginPage;
import org.velihangozek.automation.utils.ConfigReader;
import org.velihangozek.automation.utils.DriverManager;
import org.junit.jupiter.api.*;

public class EtsyTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeAll
    public static void setup() {
        DriverManager.getDriver().get(ConfigReader.getProperty("baseURL"));
    }

    @BeforeEach
    public void initPages() {
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Test
    @DisplayName("Test Login Functionality")
    public void testLogin() {
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        Assertions.assertTrue(DriverManager.getDriver().getTitle().contains("Etsy"), "User is not logged in");
    }

    @Test
    @DisplayName("Test Product Search")
    public void testSearchProduct() {
        homePage.searchProduct("handmade necklace");
        Assertions.assertTrue(DriverManager.getDriver().getPageSource().contains("handmade"));
    }

    @AfterEach
    public void tearDown() {
        DriverManager.getDriver().manage().deleteAllCookies();
    }

    @AfterAll
    public static void cleanup() {
        DriverManager.closeDriver();
    }
}