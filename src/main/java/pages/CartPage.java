package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By goToCartButton = By.id("sw-gtc");
    By cartPrice = By.cssSelector(".sc-price");

    // Methods
    public void goToCart() {
        WebElement goToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(goToCartButton));
        goToCart.click();
    }

    public String getCartPrice() {
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPrice));
        return priceElement.getText().trim().replace("₹", "").replace(",", "").trim();
    }
}
