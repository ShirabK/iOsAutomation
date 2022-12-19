import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class NewFirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp () throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","And8");
        capabilities.setCapability("platformVersion","Android 10");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/shirab.k/Desktop/JavaApiumA/JavaApiumA/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchFirstLocator () {
        WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
        element_to_init_search.click();

        WebElement element_to_enter_search_line = waitForElementPresentByXpah(
                "//*[contains(@text,'Search…')]",
                "Cannot find search input"
        );
        element_to_enter_search_line.sendKeys("Appium");
    }

    private WebElement waitForElementPresentByXpah (String xpath,String error_message, long timeoutIntSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutIntSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        //By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByXpah (String xpath,String error_message) {
        return
                waitForElementPresentByXpah(xpath,error_message,5);
    }

    @Test
    public void firstTest() {
        System.out.println("First test run");
    }

}
