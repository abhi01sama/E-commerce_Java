package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.MyUtils;

public class HomePage {
    private MyUtils utils;

    // Locators
    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchInput;

    // Constructor
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        utils = new MyUtils(driver);
    }

    // Actions
    public void searchProduct(String product) {
    	utils.type(searchInput, product);
    	searchInput.sendKeys(Keys.ENTER);
    }
}
