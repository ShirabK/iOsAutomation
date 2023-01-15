package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject{
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    protected static String
            MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

    public void clickMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find 'My lists' button and click",
                5
        );
    }
}
