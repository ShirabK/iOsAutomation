package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListObject extends MainPageObject{

    public MyListObject(AppiumDriver driver) {
        super(driver);
    }

    protected static String
    FOLDER_BY_NAME_TPL,
    ARTICLE_BY_TITLE_TPL,
    FIRST_ARTICLE_BY_JAVA,
    SECOND_ARTICLE_BY_JAVA;

    private static String getFolderXpathByName (String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle (String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public void openFolderByName (String name_of_folder) {

        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + folder_name_xpath,
                5
        );

    }

    public void swipeByArticleToDelete(String article_title) {

        this.waitForArticleToAppearByTitle(article_title);

        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(
                    article_xpath,
                    "Cannot find saved article"
            );
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void swipeFirstArticleToDeleteForIOS() {

        this.swipeElementToLeft(
                FIRST_ARTICLE_BY_JAVA,
                "Cannot find saved article"
        );

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(
                    FIRST_ARTICLE_BY_JAVA,
                    "Cannot find saved article"
            );
        }

        this.waitForArticleToDisappearByTitle(FIRST_ARTICLE_BY_JAVA);
    }

    public void swipeSecondArticleToDeleteForIOS() {

        this.swipeElementToLeft(
                SECOND_ARTICLE_BY_JAVA,
                "Cannot find saved article"
        );

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(
                    SECOND_ARTICLE_BY_JAVA,
                    "Cannot find saved article"
            );
        }

        this.waitForArticleToDisappearByTitle(SECOND_ARTICLE_BY_JAVA);
    }

    public void waitForArticleToAppearByTitle (String article_title) {

        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_xpath,
                5
        );
    }

    public void waitForArticleToDisappearByTitle (String article_title) {

        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_xpath,
                5
        );
    }

    public void waitForArticleToDisappearByTitleForIOSFirstArticle() {

        this.waitForElementNotPresent(
                FIRST_ARTICLE_BY_JAVA,
                "Saved article still present with title " + FIRST_ARTICLE_BY_JAVA,
                5
        );
    }
    public void waitForArticleToDisappearByTitleForIOSSecondArticle() {

        this.waitForElementNotPresent(
                SECOND_ARTICLE_BY_JAVA,
                "Saved article still present with title " + SECOND_ARTICLE_BY_JAVA,
                5
        );
    }
}
