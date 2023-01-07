package Tasks;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class EX5 {
    public AppiumDriver driver;
    @Before
    public void setUp () throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","And8");
        capabilities.setCapability("platformVersion","Android 10.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/shirab.k/Desktop/JavaApiumA/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutIntSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutIntSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return
                waitForElementPresent(by,error_message,5);
    }

    private boolean waitForElementNotPresent (By by, String error_message, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick (By by, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys (By by, String value, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by,error_message, timeoutSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear (By by, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by,error_message, timeoutSeconds);
        element.clear();
        return element;
    }

    private String waitForElementAndGetAttribute (By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by,error_message, timeoutInSeconds);
        return element.getText();
    }

    private void swipeUp (int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x,start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x,end_y)
                .release()
                .perform();
    }

    private void swipeUpQuick () {
        swipeUp(200);
    }

    private void swipeUpToFindElement (By by, String error_message, int max_swipes) {
        int already_swipe = 0;
/*        driver.findElements(by);
        driver.findElements(by).size();*/

        while (driver.findElements(by).size() == 0) {

            if (already_swipe > max_swipes) {
                waitForElementPresent(by, "\nCannot find element by swipe up.\n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swipe;
        }
    }

    private void swipeElementOfLeft (By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);

        action.press(right_x,middle_y)
                .waitAction(150)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements (By by) {
        List element = driver.findElements(by);
        return element.size();
    }

    private void assertElementNotPresent (By by, String error_message) {
        int amount_of_element = getAmountOfElements(by);

        if (amount_of_element > 0) {
            String default_message = "An element '" + by.toString() + "' supported to be to not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private void assertElementPresent (By by, String error_message, long timeoutInSeconds) {
        int found_element = getAmountOfElements(by);

        if (found_element == 0) {
            String message = "An element " + by.toString() + " not present";
            throw new AssertionError(message + " " + error_message );
        }
    }

    @Test
    public void testSavingTwoArticle () {
        String search_text = "Anime";
        String name_of_folder = "Japanese animation article";
        String first_article = "Japanese animation";
        String second_article = "Western animation inspired by anime (Japanese animation)";

        String folder_locator = "//*[@text='Japanese animation article']";
        String first_article_locator = "//*[@text='Japanese animation']";
        String second_article_locator = "//*[@text='Western animation inspired by anime (Japanese animation)']";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia input'",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_text,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        first_article_locator
                ),
                "Cannot find " + first_article + " topic searching by 'Java'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button to add article to reading options",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button to 'Got it' tip overlay",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article folder",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot clear input element to set name of article folder",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find or press 'OK' button to create folder",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find close article, cannot find 'x' click",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia input'",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_text,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        second_article_locator
                ),
                "Cannot find " + second_article + " topic searching by 'Java'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button to add article to reading options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_container']" +
                        folder_locator
                ),
                "Cannot find creating folder '" + name_of_folder + "'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find close article, cannot find 'x' click",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists' button and click",
                5
        );

        waitForElementAndClick(
                By.xpath(folder_locator),
                "Cannot find created folder list article",
                5
        );

        waitForElementPresent(
                By.xpath(first_article_locator),
                "Cannot find first added article '" + first_article + "'",
                5
        );

        waitForElementPresent(
                By.xpath(second_article_locator),
                "Cannot find second added article '" + second_article + "'",
                5
        );

        swipeElementOfLeft(
                By.xpath(second_article_locator),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath(second_article_locator),
                "Cannot delete saved article",
                5
        );
    }

    @Test
    public void testSearchTitleArticle () {
        String search_element = "//*[@resource-id='org.wikipedia:id/view_page_header_container']" +
                "//*[@resource-id='org.wikipedia:id/view_page_title_text']";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia input'",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                5
        );

        assertElementPresent(
            By.xpath(search_element),
                "We not found search element",
                10
        );
    }
}
