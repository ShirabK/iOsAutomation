package Tasks;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;

public class EX2 extends CoreTestCase {

    @Test
    public void testElementHasText () {
        if (Platform.getInstance().isIOS()) {
            CoreTestCase CoreTestCase = new CoreTestCase();
            CoreTestCase.skipWelcomePageForIOSApp();
        }

        String locator = "//*[@resource-id='org.wikipedia:id/search_src_text']";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        MainPageObject MainPageObject = new MainPageObject(driver);
        MainPageObject.assertElementHasTex(locator,
                "Java",
                "Search field not contain 'Java' text",
                5);

    }
}
