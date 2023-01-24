package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;

public class MyListsTests extends CoreTestCase {
    public void testSaveFirstArticleToMyListTitle() {
        if (Platform.getInstance().isIOS()) {
            CoreTestCase CoreTestCase = new CoreTestCase();
            CoreTestCase.skipWelcomePageForIOSApp();
        }

        String name_of_folder = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
        }

        ArticlePageObject.getArticleTitle();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.GoToMainPage();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
            MyListPageObject.swipeByArticleToDelete(article_title);
        } else {
            NavigationUI.closeAuthPage();
            MyListPageObject.swipeByArticleToDeleteForIOS();
        }

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.waitForArticleToDisappearByTitle(article_title);
        } else {
            MyListPageObject.waitForArticleToDisappearByTitleForIOS();
        }

    }
}
