package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By goToCartButton = By.id("sw-gtc");
    private By cartPrice = By.cssSelector(".sc-price");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToCart() {
        WebElement goToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(goToCartButton));
        goToCart.click();
    }

    public String getCartPrice() {
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPrice));
        return priceElement.getText().trim().replace("₹", "").replace(",", "").trim();
    }
}
