package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    protected static String
            MY_LISTS_LINK,
            CLOSE_AUTH_IN_SAVED_PAGE;

    public void clickMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find 'My lists' button and click",
                5
        );
    }

    public void closeAuthPage () {
        this.waitForElementAndClick(
                CLOSE_AUTH_IN_SAVED_PAGE,
                "Cannot find 'X' button and click on AuthPage",
                5
        );
    }
}
