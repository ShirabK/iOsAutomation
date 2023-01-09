package lib.ui;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {
    public WelcomePageObject (AppiumDriver driver) {
        super(driver);
    }

    private static final String
    LEARN_MORE_LINK = "Learn more about Wikipedia",
    NEXT_BUTTON = "Next",
    NEW_WAYS_TO_EXPLORE = "New ways to explore",
    ADD_OR_EDIT_PREFERRED_LANG_TEXT = "Add or edit preferred languages",

    LEARN_MORE_ABOUT_DATA_COLLECT_TEXT= "Learn more about data collected",
    GET_STARTED = "Get started";


    public void waitForLearnMoreLink () {
        this.waitForElementPresent(By.id(LEARN_MORE_LINK),
                "Cannot find 'learn More' link",
                10);
    }

    public void clickNextButton () {
        this.waitForElementAndClick(By.id(NEXT_BUTTON),
                "Cannot find 'NEXT' button and click",
                10);
    }

    public void waitForNewWayToExploreText () {
        this.waitForElementPresent(By.id(NEW_WAYS_TO_EXPLORE),
                "Cannot find 'learn More' link",
                10);
    }

    public void waitForAddOrEditPreferredLangText () {
        this.waitForElementPresent(By.id(ADD_OR_EDIT_PREFERRED_LANG_TEXT),
                "Cannot find 'Add or edit preferred languages' link",
                10);
    }

    public void waitForLearnMoreAboutDataCollectedText () {
        this.waitForElementPresent(By.id(LEARN_MORE_ABOUT_DATA_COLLECT_TEXT),
                "Cannot find 'Learn more about data collected' link",
                10);
    }

    public void clickGetStartedButton () {
        this.waitForElementAndClick(By.id(GET_STARTED),
                "Cannot find 'Get started' button and click",
                10);
    }
}
