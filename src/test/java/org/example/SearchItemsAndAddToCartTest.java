package org.example;

import org.example.pageObject.SearchProductsPage;
import org.example.utils.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SearchItemsAndAddToCartTest {
    private WebDriver driver;
    private SearchProductsPage searchProductsPage;

    @Before
    public void setUpDriver(){
        driver = WebDriverManager.driveConfig();
        searchProductsPage = new SearchProductsPage(driver);
    }

    @Test
    public void searchItemAndAddToCart(){
        searchProductsPage.addSearchTerm("camisa");
        searchProductsPage.clickSearchButton();
        searchProductsPage.selectRandomItem();
        searchProductsPage.selectNumberOfItemsToBuy();
        searchProductsPage.goToCar();
        searchProductsPage.totalToPay();
    }

    @After
    public void tearDown() {
        // Cierra el navegador después de la prueba
        if (driver != null) {
            driver.quit();
        }
    }

}
