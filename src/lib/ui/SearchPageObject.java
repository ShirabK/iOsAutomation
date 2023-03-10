package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_CANCEL_BUTTON, //"org.wikipedia:id/search_close_btn"
        SEARCH_CLEAR_FIELD_IOS,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_ELEMENT;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    // Template methods
    private static String getResultSearchElement (String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    // Template methods

    public void initSearchInput () {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find search and click search init element", 5);
    }

    public void typeSearchLine (String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult (String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubString (String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 5);
    }

    public void waitForCancelButtonToAppear () {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear () {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch () {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void clickSearchSearch () {
        this.waitForElementAndClick(SEARCH_CLEAR_FIELD_IOS, "Cannot find and click search cancel button", 5);
    }

    public int getAmountOfFoundArticle () {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                5
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel () {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Cannot find empty result element", 5);
    }

    public void waitForSomeOneResultSearch () {
        this.waitForElementNotPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Nothing was found for the specified query", 5);
    }

    public void waitForEmptySearchContainerAfterClear() {
        this.waitForElementNotPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Search container has not been cleaned", 5);
    }

    public void assertThereIsNoResultOfSearch () {
        this.waitForElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results",5);
    }
}
