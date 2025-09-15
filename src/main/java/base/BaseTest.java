package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import driver.DriverFactory;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver("chrome");  // can be chrome, firefox etc.
        driver.get("https://yourapp.com");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
