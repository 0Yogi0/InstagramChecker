package org.example.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.example.pom.BasePage;
import org.example.utilities.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static junit.framework.Assert.*;

public class Steps {

    BasePage basePage;
    WebDriver driver;



    @Given("^I am on login page$")
    public void user_is_on_login_page(){
        String chromeDriverPath = "src/main/resources/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromeDriverPath);
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.instagram.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        UtilityClass.waitForPageToLoad(driver,5);
    }

    @When("^I enter username and I click login button$")
    public void enter_username_and_click_on_login_button(){
        basePage.loginWithOnlyUsername();
    }

    @Then("^I validate the user is not logged in$")
    public void userIsNotLoggedIn(){
        assertTrue(basePage.checkIfLoginButtonIsClickable());
        if (!basePage.checkIfLoginButtonIsClickable()){
            System.out.println();
        }
        driver.quit();
    }

    @When("I enter {string} and I enter {string} and I click on login button")
    public void i_enter_username_and_i_enter_password_and_i_click_on_login_button(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        basePage.login_with_valid_username_and_password(string,string2);
        throw new io.cucumber.java.PendingException();
    }

    @Then("I validate the user is able to login")
    public void i_validate_the_user_is_able_to_login() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
