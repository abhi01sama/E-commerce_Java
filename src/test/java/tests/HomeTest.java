package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class HomeTest extends BaseTest{
	
	@Test
	public void SearchProduct() throws InterruptedException {
		HomePage homepage = new HomePage(driver);
		String productName = "ASUS Gaming V16";
		homepage.searchProduct(productName);
		
		List<WebElement> productTitles = driver.findElements(By.xpath("//h2[contains(@class, 'a-size-medium')]//span"));
		System.out.println(productTitles.size());
		
        // Loop through titles to find exact match
        for (WebElement title : productTitles) {
            String getProductName = title.getText().trim();
            System.out.println(getProductName);
            if (getProductName.contains(productName)) {
                System.out.println("✅ Found match: " + productName);
                
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", title);
                // Get the parent <a> of the product title
                WebElement linkElement = title.findElement(By.xpath("./ancestor::a"));

                // Extract the href
                String productLink = linkElement.getAttribute("href");

                // Fix relative URL issue
                if (!productLink.startsWith("http")) {
                    productLink = "https://www.amazon.in" + productLink;
                }

                // ✅ Open only once (don’t click, just navigate)
                driver.get(productLink);


                break; // Stop after first match
            }
        }
        
        Thread.sleep(3000);
        
        // Get the element containing the price + discount text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement priceElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-price-whole']"))
        );

        String price = priceElement.getText().trim();
        System.out.println("Extracted Price: ₹" + price);
        
		Thread.sleep(3000);
	}

}
