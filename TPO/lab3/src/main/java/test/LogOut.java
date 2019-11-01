package test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.annotations.Test;

public class LogOut extends BaseConfiguration {

    @Test(priority = 601)
    void tryToLogOut() {
        String menuForLogOut = "//android.view.View[@content-desc=\"Menu, Tab 5 of 5\"]";
        String logOutBtn = "//android.view.ViewGroup[@content-desc=\"Log Out, Button 1 of 1\"]";
        MobileElement menu = driver.findElementByXPath(menuForLogOut);
        menu.click();

        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Dmitriy Kurnikov\"]");

        AndroidTouchAction touchAction = new AndroidTouchAction(driver);
        touchAction.longPress(ElementOption.point(0,2200)).moveTo(ElementOption.point(0,600)).release().perform();

        MobileElement btnLogOut = driver.findElementByXPath(logOutBtn);
        btnLogOut.click();
    }

    @Test(priority = 602)
    void removeAccountFromDevice() throws InterruptedException {
        String logo = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ImageView";
        driver.findElementByXPath(logo);
        Thread.sleep(1000);
        (new TouchAction(driver)).tap(PointOption.point(600, 1800)).perform();

        MobileElement el1 = driver.findElementByXPath("//android.widget.Button[@content-desc=\"Menu\"]");
        el1.click();

        String removeBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView" +
                "/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout" +
                "/android.widget.TextView";
        MobileElement removeBtn = driver.findElementByXPath(removeBtnPath);
        removeBtn.click();
    }

    @Test(priority = 603)
    void confirmToRemove(){
        String btn = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.Button[2]\n";

        MobileElement confirmBtn = driver.findElementByXPath(btn);
        confirmBtn.click();
    }
}
