package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListObject;

public class IOSMyListsPageObject extends MyListObject {
    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL= "xpath://*[@text='{TITLE}']";
    }

}
