package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidMyListsPageObject;
import lib.ui.MyListObject;
import lib.ui.iOS.IOSMyListsPageObject;

public class MyListsPageObjectFactory{
    public static MyListObject get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else {
            return new IOSMyListsPageObject(driver);
        }
    }
}
