package tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties config;
    protected WebDriverWait wait;
    @BeforeMethod
    public void setUp() throws IOException {
        config = new Properties();
        FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
        config.load(ip);

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-features=SafeBrowsingPasswordCheck");
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(config.getProperty("url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName());
        }

        System.out.println("Test Finished: " + result.getName() + " | Status: " +
                (result.getStatus() == ITestResult.SUCCESS ? "PASSED" : "FAILED"));

        if (driver != null) {
            driver.quit();
        }
    }

    public void takeScreenshot(String testName) {
        File directory = new File("screenshots");
        if (!directory.exists()) directory.mkdir();

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(srcFile.toPath(), new File("screenshots/" + testName + ".png").toPath());
            System.out.println("Screenshot captured for failed test: " + testName);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}