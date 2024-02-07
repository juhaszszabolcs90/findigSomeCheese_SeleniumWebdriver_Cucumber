package findigSomeCheese_SeleniumWebdriver_Cucumber;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {

    private final WebDriver driver = new ChromeDriver();

    @Given("I am on the Google search page")
    public void I_visit_Google_Search_Page() {
        driver.get("https://www.google.com");
        // There is a popup asking to accept the cookies
//        WebElement acceptAll = driver.findElement(By.className("QS5gu sy4vM"));
        WebElement acceptAll = driver.findElement(By.cssSelector(".QS5gu.sy4vM"));
        acceptAll.click();
    }

    @When("I search for {string}")
    public void search_for(String query) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    @Then("the page title should start with {string}")
    public void check_page_title(String titleStartsWith) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.getTitle().toLowerCase().startsWith(titleStartsWith);
            }
        });
//        assertEquals(titleStartsWith, driver.getTitle().toLowerCase());
        String actualTitle = driver.getTitle().toLowerCase();
//        assertTrue(actualTitle.startsWith(titleStartsWith));
        assertTrue(actualTitle.contains(titleStartsWith));
        System.out.println("Actual title:__ " + actualTitle);
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}
