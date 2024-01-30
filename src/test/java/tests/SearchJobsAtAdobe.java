package tests;

import dataProviders.CalculatorDataProviders;
import dataProviders.NumberOfAdobeJobsDataProviders;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;


public class SearchJobsAtAdobe {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void testVerifyAdobeJobsCount() {
        driver = new ChromeDriver();
        driver.get("https://careers.adobe.com/us/en");

        WebElement searchInput = driver.findElement(By.className("ph-a11y-search-box"));
        searchInput.sendKeys("Bucharest", Keys.ENTER);

        WebElement resultsCount = driver.findElement(By.className("result-count"));
        int numberOfJobs = Integer.parseInt(resultsCount.getText());
        Assert.assertTrue(numberOfJobs > 10);
    }

    @Test(dataProvider = "numberOfJobsDataProvider", dataProviderClass = NumberOfAdobeJobsDataProviders.class)
    public void testVerifyAdobeJobsCountUsingDataProviders(int testNumber, boolean exceptedResult) {
        driver = new ChromeDriver();
        driver.get("https://careers.adobe.com/us/en");

        WebElement searchInput = driver.findElement(By.className("ph-a11y-search-box"));
        searchInput.sendKeys("Bucharest", Keys.ENTER);

        WebElement resultsCount = driver.findElement(By.className("result-count"));
        int numberOfJobs = Integer.parseInt(resultsCount.getText());
        Assert.assertEquals(numberOfJobs > testNumber, exceptedResult);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
//            driver.quit();
        }
    }
}
