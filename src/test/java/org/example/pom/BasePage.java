package org.example.pom;

import org.example.utilities.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    public UtilityClass utilityClass;

    public String getUserID() {
        return userID;
    }

    private final String userID = "username";
    private final String url = "https://www.instagram.com/";


    public BasePage(WebDriver driver){
        this.driver = driver;
        utilityClass = new UtilityClass(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//*[@id='loginForm']/div/div[2]/div/label/input")
    private WebElement password;

    @FindBy(how = How.XPATH, using = "//*[@id='loginForm']/div/div[3]")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//*[@id='loginForm']/div/div[1]/div/label/input")
    private WebElement username;


    public void login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.visibilityOf(username));
        UtilityClass.enterText(username, getUserID());
        UtilityClass.enterText(password, UtilityClass.getCredentialsFromFile());
        UtilityClass.clickElement(loginButton);
    }

    public void loginWithOnlyUsername(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(username));
        UtilityClass.enterText(username, getUserID());
        UtilityClass.clickElement(loginButton);
    }


    public String getUrl() {
        return url;
    }

    public boolean checkIfLoginButtonIsClickable(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(loginButton));
       return UtilityClass.isButtonClickable(loginButton);
    }

    public void login_with_valid_username_and_password(String user, String pass){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.visibilityOf(username));
        UtilityClass.enterText(username,user);
        UtilityClass.enterText(password,pass);
        UtilityClass.clickElement(loginButton);

    }
}
