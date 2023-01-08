package Tasks;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;
public class EX5 extends CoreTestCase {

    @Test
    public void testSavingTwoArticle () {
/*        String search_text = "Anime";
        String name_of_folder = "Japanese animation article";
        String first_article = "Japanese animation";
        String second_article = "Western animation inspired by anime (Japanese animation)";

        String folder_locator = "//*[@text='Japanese animation article']";
        String first_article_locator = "//*[@text='Japanese animation']";
        String second_article_locator = "//*[@text='Western animation inspired by anime (Japanese animation)']";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia input'",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_text,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        first_article_locator
                ),
                "Cannot find " + first_article + " topic searching by 'Java'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button to add article to reading options",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button to 'Got it' tip overlay",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article folder",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot clear input element to set name of article folder",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find or press 'OK' button to create folder",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find close article, cannot find 'x' click",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia input'",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_text,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        second_article_locator
                ),
                "Cannot find " + second_article + " topic searching by 'Java'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button to add article to reading options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_container']" +
                        folder_locator
                ),
                "Cannot find creating folder '" + name_of_folder + "'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find close article, cannot find 'x' click",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists' button and click",
                5
        );

        waitForElementAndClick(
                By.xpath(folder_locator),
                "Cannot find created folder list article",
                5
        );

        waitForElementPresent(
                By.xpath(first_article_locator),
                "Cannot find first added article '" + first_article + "'",
                5
        );

        waitForElementPresent(
                By.xpath(second_article_locator),
                "Cannot find second added article '" + second_article + "'",
                5
        );

        swipeElementOfLeft(
                By.xpath(second_article_locator),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath(second_article_locator),
                "Cannot delete saved article",
                5
        );*/

        String name_of_folder = "Japanese animation article";
        String search_text = "Anime";
        String first_article = "Japanese animation";
        String second_article = "Western animation inspired by anime (Japanese animation)";

        //Add first article
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_text);
        SearchPageObject.clickByArticleWithSubString(first_article);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.getArticleTitle();
        String article_first_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        //Add second article
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_text);
        SearchPageObject.clickByArticleWithSubString(second_article);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.getArticleTitle();
        String article_second_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addiArticleToCreatedFolderInMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        //Go to My list to remove one of article
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListObject MyListPageObject = new MyListObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(second_article);
        MyListPageObject.waitForArticleToDisappearByTitle(second_article);

        MyListPageObject.waitForArticleToAppearByTitle(first_article);
    }
}
