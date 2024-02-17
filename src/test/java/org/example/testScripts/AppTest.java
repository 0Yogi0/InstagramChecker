package org.example.testScripts;

import org.example.pom.BasePage;
import org.example.pom.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AppTest
{
    WebDriver driver;
    BasePage basePage;

    HomePage homePage;

    @BeforeMethod
    public void setBasePage(){
        String chromeDriverPath = "src/main/resources/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromeDriverPath);
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.instagram.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    /*@AfterMethod
    public void teardown(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }*/


    @Test
    public void loginTest() throws InterruptedException {
        homePage.navigateToHomePage();
        homePage.navigateToProfilePage();
        homePage.followersComparator();

    }
}
