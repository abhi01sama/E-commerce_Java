package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver(String browser) {

        if (driver == null) {

            if ("chrome".equalsIgnoreCase(browser)) {

                ChromeOptions options = new ChromeOptions();

                // ✅ Mandatory for CI (GitHub Actions / Linux)
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");

                // ✅ Reduce automation detection
                options.addArguments("--disable-blink-features=AutomationControlled");

                options.setExperimentalOption("excludeSwitches",
                        new String[]{"enable-automation"});
                options.setExperimentalOption("useAutomationExtension", false);

                driver = new ChromeDriver(options);

            } else if ("firefox".equalsIgnoreCase(browser)) {

                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("-headless");

                driver = new FirefoxDriver(options);

            } else {
                throw new RuntimeException("Unsupported browser: " + browser);
            }

        }

        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call initDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
