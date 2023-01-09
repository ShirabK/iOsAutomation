package tests.iOS;

import lib.iOSCoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class getStartedTest extends iOSCoreTestCase {
    @Test
    public void testPassThroughWelcome () {
        WelcomePageObject WelcomePageObject = new WelcomePageObject (driver);

        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWayToExploreText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForAddOrEditPreferredLangText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForLearnMoreAboutDataCollectedText();
        WelcomePageObject.clickGetStartedButton();
    }
}
