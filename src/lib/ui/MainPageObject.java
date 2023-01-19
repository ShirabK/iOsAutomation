package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject (AppiumDriver driver) {

        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutIntSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutIntSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return
                waitForElementPresent(locator,error_message,5);
    }

    public boolean waitForElementNotPresent (String locator, String error_message, long timeoutSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClick (String locator, String error_message, long timeoutSeconds) {
        By by = this.getLocatorByString(locator);
        WebElement element = waitForElementPresent(locator, error_message, timeoutSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys (String locator, String value, String error_message, long timeoutSeconds) {
        By by = this.getLocatorByString(locator);
        WebElement element = waitForElementPresent(locator,error_message, timeoutSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear (String locator, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(locator,error_message, timeoutSeconds);
        element.clear();
        return element;
    }

    public String waitForElementAndGetAttribute (String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator,error_message, timeoutInSeconds);
        return element.getText();
    }

    public void swipeUp (int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                /*.press(x, start_y)*/
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void swipeUpQuick () {
        swipeUp(200);
    }

    public void swipeUpToFindElement (String locator, String error_message, int max_swipes) {
        By by = this.getLocatorByString(locator);
        int already_swipe = 0;
/*        driver.findElements(by);
        driver.findElements(by).size();*/

        while (driver.findElements(by).size() == 0) {

            if (already_swipe > max_swipes) {
                waitForElementPresent(locator, "\nCannot find element by swipe up.\n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swipe;
        }
    }

    public void swipeUpTitleElementAppear (String locator, String error_message, int max_swipes) {
        int already_swipe = 0;

        while (!this.isElementLocatedOmTheScreen(locator)) {
            if (already_swipe > max_swipes) {
                Assert.assertTrue(error_message,this.isElementLocatedOmTheScreen(locator));
            }

            swipeUpQuick();
            ++already_swipe;
        }
    }

    public boolean isElementLocatedOmTheScreen (String locator) {
        int element_location_by_y = this.waitForElementPresent(locator,"Cannot find element by locator", 1)
                .getLocation().getY();
        int screen_size_by = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by;
    }

    public void clickElementToTheRightUpperCorner (String locator, String error_message){
        WebElement element = this.waitForElementPresent(locator,error_message); //(locator + "/..",error_message)

        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int wight = element.getSize().getWidth();

        int point_to_click_x = (right_x + wight) - 3;
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(point_to_click_x,point_to_click_y)).perform();
        //(PointOption.point(point_to_click_x,point_to_click_y))

    }

    public void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(
                locator,
                error_message,
                10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x,middle_y));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)));

        if (Platform.getInstance().isAndroid()) {
            action.moveTo(PointOption.point(left_x,middle_y));
        } else {
            int offset_x = (-1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(offset_x,0));
        }

        action.release();
        action.perform();
    }

    public int getAmountOfElements (String locator) {
        By by = this.getLocatorByString(locator);
        List element = driver.findElements(by);
        return element.size();
    }

    public void assertElementNotPresent (String locator, String error_message) {
        int amount_of_element = getAmountOfElements(locator);

        if (amount_of_element > 0) {
            String default_message = "An element '" + locator + "' supported to be to not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public WebElement assertElementHasTex (String locator, String expected_text, String error_message, long timeoutSeconds) {
        WebElement text = waitForElementPresent(locator, error_message, timeoutSeconds);

        String expected = text.getText();

        Assert.assertEquals(
                error_message,
                expected_text,
                expected
        );

        return text;
    }

    public void assertElementPresent (String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        int found_element = getAmountOfElements(locator);

        if (found_element == 0) {
            String message = "An element " + locator + " not present";
            throw new AssertionError(message + " " + error_message );
        }
    }

    private By getLocatorByString (String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator " + locator_with_type);
        }
    }
}
