package org.example.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UtilityClass {
    protected final WebDriver driver;

    private  static final String filePath = "D:\\passwordFolder\\file.txt\\";
    public UtilityClass(WebDriver driver) {
        this.driver = driver;
    }

    public void openURL(String url) {
        driver.get(url);
    }

    public static void clickElement(WebElement element) {
        element.click();
    }

    public static void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public void clearText(WebElement element) {
        element.clear();
    }

    public void submitForm(WebElement element) {
        element.submit();
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    public static void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
        ExpectedCondition<Boolean> pageLoadCondition = webDriver -> {
            if (webDriver != null) {
                return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
            }
            System.out.println("Driver is null");
            return false;
        };

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(pageLoadCondition);
    }

    public static String getCredentialsFromFile(){
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("No lines present in file");
            throw new RuntimeException(e);
        }
        return lines.get(0);
    }

    public static boolean isButtonClickable(WebElement button) {
        try {

            return button.isEnabled() && button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static WebElement waitForElementVisibility(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static List<WebElement> getWebElementList(WebDriver driver, String byPath, int iterable) {
        List<WebElement> elementList = new ArrayList<>();

        for (int i = 1; i <= iterable; i++) {
            elementList.add(driver.findElement(By.xpath(byPath+"["+i+"]")));
        }

        return elementList;
    }

    public static List<String> getTextList(ArrayList<WebElement> elements) {
        List<String> textList = new ArrayList<>();

        for (WebElement element : elements) {
            textList.add(element.getText());
        }

        return textList;
    }


    public static void hoverAndHighlight(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        highlightElement(driver, element, "red"); // Highlight in red before hovering
        actions.moveToElement(element).perform();
        highlightElement(driver, element, "red"); // Remove the highlight after hovering
    }

    private static void highlightElement(WebDriver driver, WebElement element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='2px solid " + color + "'", element);
    }


    public static void focusOnElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void scrollDown(WebDriver driver, WebElement eleToScroll) {
        hoverAndHighlight(driver,eleToScroll);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2000);"); // Adjust the scroll distance as needed
    }



    public static void scrollToBottomOfDiv(WebDriver driver, WebElement divElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        long initialHeight;
        long currentHeight = (long) js.executeScript("return arguments[0].scrollHeight", divElement);

        do {
            initialHeight = currentHeight;
            js.executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight);", divElement);

            // Wait for some time to allow new elements to load (you may need to adjust the duration)
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentHeight = (long) js.executeScript("return arguments[0].scrollHeight", divElement);
        }
        while (currentHeight > initialHeight);
    }


    public static void printNonCommonElements(List<String> list1, List<String> list2) {
        List<String> nonCommonElements = new ArrayList<>(list1);
        nonCommonElements.removeAll(list2);

        List<String> temp = new ArrayList<>(list2);
        temp.removeAll(list1);

        nonCommonElements.addAll(temp);

        System.out.println("Elements not common in both lists:");
        for (String element : nonCommonElements) {
            System.out.println(element);
        }
    }

}
