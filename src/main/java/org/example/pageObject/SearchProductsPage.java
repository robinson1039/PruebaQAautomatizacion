package org.example.pageObject;

import org.example.locators.CarLocators;
import org.example.locators.ItemsLocators;
import org.example.locators.SearchLocators;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class SearchProductsPage {
    private static final Logger log = LoggerFactory.getLogger(SearchProductsPage.class);
    private WebDriver driver;

    public SearchProductsPage(WebDriver driver){
        this.driver = driver;
    }

    public void addSearchTerm(String searchTerm){
        WebDriverWait wait=  new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchItems = wait.until(ExpectedConditions.visibilityOfElementLocated(SearchLocators.SEARCH_INPUT));
        searchItems.clear();
        searchItems.sendKeys(searchTerm);
    }

    public void clickSearchButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(SearchLocators.SEARCH_BUTTON));
        searchButton.click();
    }

    public void selectRandomItem(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> items =wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SearchLocators.SELECT_ITEM));

        if(!items.isEmpty()){
            Random random = new Random();
            int randomIndex = random.nextInt(items.size());
            WebElement randomItem =items.get(randomIndex);
            randomItem.click();
        }else{
            System.out.println("No items were found in the search results.");
        }
    }

    public void selectNumberOfItemsToBuy(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement quantityInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(ItemsLocators.NO_MORE_ITEMS));
        String actualText = quantityInfo.getText();
        String expectedText = "1 pieza(s) como máximo por cliente";
        String otherOptionForText = "Solo quedan 1";
        if (actualText.contains(expectedText) || actualText.contains(otherOptionForText)) {
            WebElement moreItems = null;
        }else{
            WebElement moreItems;
            try{

                moreItems = wait.until(ExpectedConditions.elementToBeClickable(ItemsLocators.MORE_ITEMS));
            }catch (StaleElementReferenceException error){
                log.error("e: ", error);
                moreItems = null;
            }
            int numberOfItems = 3;
            for (int i = 0; i <= numberOfItems; i++) {
                try {
                    moreItems.click();
                }catch (StaleElementReferenceException e){
                    log.error("e: ", e);
                }
            }
        }

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(ItemsLocators.ADD_ITEM_TO_CAR));
        addToCartButton.click();
    }

    public void goToCar(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement carButton = wait.until(ExpectedConditions.elementToBeClickable(CarLocators.GO_TO_CAR));
        carButton.click();
    }

    public void totalToPay(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement infoPay = wait.until(ExpectedConditions.visibilityOfElementLocated(CarLocators.ESTIMATED_TOTAL));
        String getInfo =infoPay.getText();
        System.out.println(getInfo);
    }
}
