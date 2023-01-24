package Tasks;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class EX3 extends CoreTestCase {

    @Test
    public void  testClearSearchAfterRequest() {
        if (Platform.getInstance().isIOS()) {
            CoreTestCase CoreTestCase = new CoreTestCase();
            CoreTestCase.skipWelcomePageForIOSApp();
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.waitForSomeOneResultSearch();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForEmptySearchContainerAfterClear();
    }
}
