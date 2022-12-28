package Tasks;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EX5 {
    private AppiumDriver driver;
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
}
