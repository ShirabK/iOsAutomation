package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.xml.bind.Element;

public class ArticlePageObject extends MainPageObject {
    private static final String
    TITLE = "//*[@resource-id='org.wikipedia:id/view_page_title_text']",
    FOOTER_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_external_link']",
    OPTIONS_BUTTON = "//*[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY= "//*[@resource-id='org.wikipedia:id/onboarding_button']",
    MY_LIST_NAME_INPUT = "//*[@resource-id='org.wikipedia:id/text_input']",
    MY_LIST_OKAY_BUTTON = "//*[@resource-id='android:id/button1']",
    CLOSE_ARTICLE_BUTTON = "//*[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement () {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page", 5);
    }

    public String getArticleTitle () {
        WebElement title_element = waitForTitleElement();
        return title_element.getText();
    }

    public void swipeToFooter () {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the and of article",
                20
        );
    }

    public void addArticleToMyList (String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find button to add article to reading options",
                5
        );

        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find button to 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of article folder",
                5
        );

        this.waitForElementAndClear(
                By.xpath(MY_LIST_NAME_INPUT),
                "Cannot clear input element to set name of article folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.xpath(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OKAY_BUTTON),
                "Cannot find or press 'OK' button to create folder",
                5
        );
    }

    public void closeArticle () {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot find close article, cannot find 'x' click",
                5
        );
    }
}
