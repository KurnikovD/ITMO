package test;

import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;

public class Profile extends BaseConfiguration {

    @Test(priority = 500)
    void goToMainPage(){
        String mainPage = "//android.view.View[@content-desc=\"News Feed, Tab 1 of 5\"]";

        MobileElement btn = driver.findElementByXPath(mainPage);
        btn.click();
    }

    @Test(priority = 501)
    void openStatusForCreate(){
        String status = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView" +
                "/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[2]";

        MobileElement btn = driver.findElementByXPath(status);
        btn.click();
    }

    @Test(priority = 502)
    void printStatus(){
        String messege = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.EditText";

        MobileElement field = driver.findElementByXPath(messege);
        field.sendKeys("Mi first status");
    }

    @Test(priority = 503)
    void clickPost(){
        MobileElement btn = driver.findElementByXPath("//android.widget.Button[@content-desc=\"POST\"]");
        btn.click();
    }

    @Test(priority = 504)
    void menuClick(){
        MobileElement btn = driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\"Post Menu\"])[1]");
        btn.click();
    }

    @Test(priority = 505)
    void delete(){
        MobileElement btn = driver.findElementByXPath("//android.widget.Button[@content-desc=\"Delete\"]");
        btn.click();
    }

    @Test(priority = 506)
    void confirm(){
        String confirm = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.Button[1]";

        MobileElement btn = driver.findElementByXPath(confirm);
        btn.click();
    }
}
