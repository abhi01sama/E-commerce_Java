package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    @FindBy(xpath = "//div[@class='a-section a-spacing-none a-padding-none']//span[@class='a-color-price']")
    private WebElement priceElement;

    @FindBy(xpath = "(//span[@id='submit.add-to-cart'])[2]")
    private WebElement addCartButton;

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions
    public String getProductPrice() {
        wait.until(ExpectedConditions.visibilityOf(priceElement));
        return priceElement.getText().trim().replace("₹", "").replace(",", "").trim();
    }

    public CartPage addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addCartButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addCartButton);
        addCartButton.click();
        return new CartPage(driver);
    }
}
