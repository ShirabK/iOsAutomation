package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.xml.bind.Element;

public class ArticlePageObject extends MainPageObject {
    private static final String
    TITLE = "xpath://*[@resource-id='org.wikipedia:id/view_page_title_text']",
    FOOTER_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_external_link']",
    OPTIONS_BUTTON = "xpath://*[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY= "xpath://*[@resource-id='org.wikipedia:id/onboarding_button']",
    MY_LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input']",
    MY_LIST_OKAY_BUTTON = "xpath://*[@resource-id='android:id/button1']",
    CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']",
    CREATED_FOLDER_IN_MY_LIST = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='{FOLDER_NAME}']";

    private static String getXpathFolderName (String folder_name) {
        return CREATED_FOLDER_IN_MY_LIST.replace("{FOLDER_NAME}", folder_name);
    }
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement () {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 5);
    }

    public String getArticleTitle () {
        WebElement title_element = waitForTitleElement();
        return title_element.getText();
    }

    public void swipeToFooter () {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the and of article",
                20
        );
    }

    public void addArticleToMyList (String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find button to add article to reading options",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find button to 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of article folder",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot clear input element to set name of article folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OKAY_BUTTON,
                "Cannot find or press 'OK' button to create folder",
                5
        );
    }

    public void addiArticleToCreatedFolderInMyList (String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find button to add article to reading options",
                5
        );

        String name_xpath = getXpathFolderName(name_of_folder);
        this.waitForElementAndClick(
                name_xpath,
                "Cannot find folder by name " + name_xpath,
                5
        );
    }

    public void closeArticle () {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find close article, cannot find 'x' click",
                5
        );
    }
}
