package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListObject;

public class IOSMyListsPageObject extends MyListObject {
    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[contains(@value,'{FOLDER_NAME}')]";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@text,'{TITLE}')]";
        FIRST_ARTICLE_BY_JAVA = "xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther[2]";
    }

}
