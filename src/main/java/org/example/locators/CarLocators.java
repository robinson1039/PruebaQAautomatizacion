package org.example.locators;

import org.openqa.selenium.By;

public class CarLocators {
    public static final By GO_TO_CAR = By.cssSelector("a[class=\"cart-summary-tocart\"]");
    public static final By ESTIMATED_TOTAL= By.xpath("//div[@class=\"cart-summary-item\" and contains(., 'Total estimado')]");
}
