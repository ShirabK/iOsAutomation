package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidNavigationUI;
import lib.ui.NavigationUI;
import lib.ui.iOS.IOSNavigationUI;

public class NavigationUIFactory {
    public static NavigationUI get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        } else {
            return new IOSNavigationUI(driver);
        }
    }
}
