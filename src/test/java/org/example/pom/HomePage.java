package org.example.pom;

import org.example.utilities.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//div[@class='_ac8f']")
    private WebElement notNowSaveInfoBtn;

    @FindBy(how = How.XPATH, using = "//button[@class='_a9-- _ap36 _a9_1']")
    private WebElement notNowNotificationBtn;

    @FindBy(how = How.XPATH, using = "(//a[@href='/the_beige_beanie/'])[1]")
    private WebElement homePageProfilePic;

    @FindBy(how = How.XPATH, using = "(//span[@class='_ac2a' ])[2]")
    private WebElement profilePageFollowerCount;

    @FindBy(how = How.XPATH, using = "(//span[@class='_ac2a'])[3]")
    private WebElement profilePageFollowingCount;

    @FindBy(how = How.XPATH, using = "//div[@class='_aano']")
    private WebElement followersDiv;

    //div[@class='_aano']
    @FindBy(how = How.XPATH, using = "//div[@class='_aano']")
    private WebElement followingDiv;


    @FindBy(how = How.XPATH, using = "(//div[@class='x1rg5ohu']//span)")
    private WebElement individualFollowers;


    @FindBy(how = How.XPATH, using = "//button[@class='_abl-']")
    private WebElement closeBtn;


    private final String individualFollowersBy = "(//div[@class='x1rg5ohu']//span)";
    private final String individualFollowingBy = "(//div[@class='x1rg5ohu']//span)";


    public void navigateToHomePage(){
        login();
        UtilityClass.waitForElementVisibility(driver,notNowSaveInfoBtn,7).click();
        UtilityClass.waitForElementVisibility(driver,notNowNotificationBtn,5).click();

    }

    public void navigateToProfilePage(){
        UtilityClass.waitForElementVisibility(driver,homePageProfilePic,3).click();
    }

    public void followersComparator(){
        int followerIterable = Integer.parseInt(UtilityClass.waitForElementVisibility(driver,profilePageFollowerCount,2).getText());
        int followingIterable = Integer.parseInt(UtilityClass.waitForElementVisibility(driver,profilePageFollowingCount,2).getText());

        UtilityClass.waitForElementVisibility(driver,profilePageFollowerCount,5).click();
        UtilityClass.waitForElementVisibility(driver,individualFollowers,3);
        UtilityClass.scrollToBottomOfDiv(driver,followersDiv);
        ArrayList<WebElement> listOfFollowerElements = (ArrayList<WebElement>) UtilityClass.getWebElementList(driver, individualFollowersBy,followerIterable);
        List<String> followers = UtilityClass.getTextList(listOfFollowerElements);
        UtilityClass.waitForElementVisibility(driver,closeBtn,5).click();

        UtilityClass.waitForElementVisibility(driver,profilePageFollowingCount,5).click();
        UtilityClass.waitForElementVisibility(driver,individualFollowers,5);
        UtilityClass.scrollToBottomOfDiv(driver,followersDiv);
        ArrayList<WebElement> listOfFollowingElements = (ArrayList<WebElement>) UtilityClass.getWebElementList(driver, individualFollowersBy,followingIterable);
        List<String> following = UtilityClass.getTextList(listOfFollowingElements);
        UtilityClass.waitForElementVisibility(driver,closeBtn,5).click();


        System.out.println(followers.size());
        System.out.println(following.size());
        UtilityClass.printNonCommonElements(following,followers);
    }










}
