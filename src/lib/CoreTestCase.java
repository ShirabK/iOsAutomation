package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;


import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;

    @Override
    protected void setUp () throws Exception{

        super.setUp();

        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
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

    private  void skipWelcomePageForIOSApp () {
        if (Platform.getInstance().isIOS()) {
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }

/*    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","And8");
            capabilities.setCapability("platformVersion","Android 10.0");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("app","/Users/shirab.k/Desktop/JavaApiumA/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName","iOS");
            capabilities.setCapability("deviceName","iPhone 8");
            capabilities.setCapability("platformVersion","12.0");
            capabilities.setCapability("app","/Users/shirab.k/Desktop/iOsAutomation/apks/Wikipedia.app");
        } else {
            throw new Exception("Cannot get platform from env variable. Platform value " + platform);
        }
        return capabilities;
    }*/
}
