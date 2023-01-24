package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;

    @Override
    protected void setUp () throws Exception{

        super.setUp();

        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception{
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait () {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape () {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp (int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    protected void SwipeUp (int timeOfSwipe) {
            TouchAction action = new TouchAction(driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width/2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
    }

    public   void skipWelcomePageForIOSApp () {
        if (Platform.getInstance().isIOS()) {
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
}
