package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.TestCase;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class iOSCoreTestCase extends TestCase {
    protected AppiumDriver driver;

    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp () throws Exception{

        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone 8");
        capabilities.setCapability("platformVersion","12.0");
        capabilities.setCapability("app","/Users/shirab.k/Desktop/iOsAutomation/apks/Wikipedia.app");

        driver = new IOSDriver(new URL(AppiumURL),capabilities);
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
                    /*.press(x, start_y)*/
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
    }
}
