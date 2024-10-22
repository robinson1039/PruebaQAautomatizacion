package org.example.locators;

import org.openqa.selenium.By;

public class ItemsLocators {
    public static final By ADD_ITEM_TO_CAR = By.cssSelector("[class*=\"add-to-cart\"]");
    public static final By MORE_ITEMS = By.cssSelector("[class=\"comet-v2-input-number-btn comet-v2-input-number-btn-increase\"]");
    public static final By NO_MORE_ITEMS = By.cssSelector("div[class=\"quantity--info--jnoo_pD\"]");
}
