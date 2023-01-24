package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testFirstTest() {
        System.out.println("First test run");
    }

    @Test
    public void testFirstSearchLocator() {
        if (Platform.getInstance().isIOS()) {
            CoreTestCase CoreTestCase = new CoreTestCase();
            CoreTestCase.skipWelcomePageForIOSApp();
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");

    }

    @Test
    public void testSearchJava() {
        if (Platform.getInstance().isIOS()) {
            CoreTestCase CoreTestCase = new CoreTestCase();
            CoreTestCase.skipWelcomePageForIOSApp();
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void  testCancelSearch() {
        if (Platform.getInstance().isIOS()) {
            CoreTestCase CoreTestCase = new CoreTestCase();
            CoreTestCase.skipWelcomePageForIOSApp();
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.clickCancelSearch();
            SearchPageObject.clickCancelSearch();
            SearchPageObject.waitForCancelButtonToDisappear();
        } else {
            SearchPageObject.clickSearchSearch();
            SearchPageObject.waitForCancelButtonToDisappearIOS();
        }
    }
}
