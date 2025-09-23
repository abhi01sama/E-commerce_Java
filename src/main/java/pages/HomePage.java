package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import utils.MyUtils;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private MyUtils utils;

    // Locators
    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchInput;

    @FindBy(xpath = "//h2[contains(@class, 'a-size-medium')]//span")
    private List<WebElement> productTitles;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utils = new MyUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions
    public void searchProduct(String product) {
        utils.type(searchInput, product);
        searchInput.sendKeys(Keys.ENTER);
    }

    public ProductPage selectProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productTitles));

        for (WebElement title : productTitles) {
            String getProductName = title.getText().trim();
            if (getProductName.contains(productName)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", title);

                WebElement linkElement = title.findElement(By.xpath("./ancestor::a"));
                String productLink = linkElement.getAttribute("href");

                if (!productLink.startsWith("http")) {
                    productLink = "https://www.amazon.in" + productLink;
                }

                driver.get(productLink);
                return new ProductPage(driver);
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }
}
