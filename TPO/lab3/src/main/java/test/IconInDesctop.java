package test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import org.apache.tools.ant.types.Assertions;
import org.testng.annotations.Test;

public class IconInDesctop extends BaseConfiguration {

    @Test(priority = 700)
    void goToDesktop(){
        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.HOME);
    }

    @Test(priority = 701)
    void goToMenu(){

        AndroidTouchAction touchAction = new AndroidTouchAction(driver);
        touchAction.press(ElementOption.point(0,2200)).moveTo(ElementOption.point(0,600)).release().perform();
    }

    @Test(priority = 702)
    void putIconInMainMenu(){
        AndroidTouchAction touchAction = new AndroidTouchAction(driver);
        touchAction.longPress(ElementOption.point(450,1200)).moveTo(ElementOption.point(1000,1000)).release().perform();
    }

    @Test(priority = 703)
    void removeFromDesctop(){
        driver.findElementByXPath("//android.appwidget.AppWidgetHostView[@content-desc=\"Search\"]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView");
        AndroidTouchAction touchAction = new AndroidTouchAction(driver);
        touchAction.longPress(ElementOption.point(1000,1000)).moveTo(ElementOption.point(250,170)).release().perform();

    }
}
