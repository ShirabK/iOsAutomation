package Tasks;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;

public class EX6 extends CoreTestCase {
    @Test
    public void testSearchTitleArticle () {
        if (Platform.getInstance().isIOS()) {
            CoreTestCase CoreTestCase = new CoreTestCase();
            CoreTestCase.skipWelcomePageForIOSApp();
        }


        String search_element = "//*[@resource-id='org.wikipedia:id/view_page_header_container']" +
                "//*[@resource-id='org.wikipedia:id/view_page_title_text']";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        MainPageObject MainPageObject = new MainPageObject (driver);

        MainPageObject.assertElementPresent(
                search_element,
                "We not found search element",
                10
        );
    }
}
