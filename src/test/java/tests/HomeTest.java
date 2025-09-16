package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
                title.click(); // Example: click to go to product page
                break; // Stop after first match
            }
        }
        
        Thread.sleep(5000);
        
        Set<String> handles = driver.getWindowHandles();
	    List<String> tabs = new ArrayList<>(handles);
        driver.switchTo().window(tabs.get(1));
        
        // Get the element containing the price + discount text
        WebElement priceElement = driver.findElement(By.xpath("//div[@id='corePriceDisplay_desktop_feature_div']//span[contains(@class, 'aok-offscreen')]"));

        // Get the full text
        String fullText = priceElement.getText(); // e.g. "₹76,790.00 with 29 percent savings"

        // Use regex to extract just the amount
        Pattern pattern = Pattern.compile("₹[\\d,]+\\.\\d{2}");
        Matcher matcher = pattern.matcher(fullText);

        if (matcher.find()) {
            String amount = matcher.group(); // This will be ₹76,790.00
            System.out.println("Extracted Amount: " + amount);
        } else {
            System.out.println("Amount not found in the text.");
        }
		Thread.sleep(3000);
	}

}
