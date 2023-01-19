package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {
    public IOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }

    static {
        MY_LISTS_LINK = "xpath://XCUIElementTypeButton[@name='Saved']";
        CLOSE_AUTH_IN_SAVED_PAGE = "xpath://XCUIElementTypeButton[@name='Close']";
    }

}
