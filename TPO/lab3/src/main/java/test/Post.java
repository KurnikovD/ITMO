package test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.annotations.Test;

public class Post extends BaseConfiguration {

    @Test(priority = 201)
    void openPost() {
        String menuForLogOut = "//android.view.View[@content-desc=\"Menu, Tab 5 of 5\"]";
        String pages = "//android.view.ViewGroup[@content-desc=\"Pages\"]";
        MobileElement menu = driver.findElementByXPath(menuForLogOut);
        menu.click();

        MobileElement pageBtn = driver.findElementByXPath(pages);
        pageBtn.click();
    }

    @Test(priority = 202)
    void openProfile() {
        String profile = "//android.view.ViewGroup[@content-desc=\"Liked Pages\"]";

        MobileElement profileBtn = driver.findElementByXPath(profile);
        profileBtn.click();

        String profile2 = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup" +
                "/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup" +
                "/android.view.ViewGroup/android.view.ViewGroup[1]";

        MobileElement btn = driver.findElementByXPath(profile2);
        btn.click();
//        (new TouchAction(driver)).tap(PointOption.point(380, 350)).perform();
    }

    @Test(priority = 205)
    void putLike(){
        String like ="//android.view.ViewGroup[@content-desc=\"like button\"]/android.view.ViewGroup/android.view.ViewGroup";

        MobileElement likeBtn = driver.findElementByXPath(like);
        likeBtn.click();
    }

    @Test(priority = 203)
    void putUnLike(){
        String unlike ="//android.view.ViewGroup[@content-desc=\"like button\"]/android.view.ViewGroup";

        MobileElement likeBtn = driver.findElementByXPath(unlike);
        likeBtn.click();
    }

    @Test(priority = 204)
    void confirmUnLike(){
        String unlike = "//android.widget.Button[@content-desc=\"Unlike\"]/android.widget.TextView";

        MobileElement likeBtn = driver.findElementByXPath(unlike);
        likeBtn.click();
    }

//    @Test(priority = 407)
//    void doFollow(){
//        MobileElement btn = driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"More\"]");
//        btn.click();
//        MobileElement followBtn = driver.findElementByXPath("//android.widget.Button[@content-desc=\"Follow\"]");
//        followBtn.click();
//
//    }

    @Test(priority = 206)
    void goToToPost(){
        
        String post = "//android.widget.TextView[@content-desc=\"Posts, Tab 3 of 10\"]";

        MobileElement postBtn = driver.findElementByXPath(post);
        postBtn.click();
    }

    @Test(priority = 207)
    void putLikeOnPost(){
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Post Menu\"]");
        AndroidTouchAction touchAction = new AndroidTouchAction(driver);
        touchAction.longPress(ElementOption.point(0,1170)).moveTo(ElementOption.point(0,92)).release().perform();

        String like = "//android.widget.TextView[@content-desc=\"Like button. Double tap and hold to react.\"]";
        MobileElement likeBtn = driver.findElementByXPath(like);
        likeBtn.click();
    }

    @Test(priority = 208)
    void putUnLikeOnPost(){
        String unLike = "//android.widget.TextView[@content-desc=\"Like button, pressed. Double tap and hold to change reaction.\"]";

        MobileElement unLikeBtn = driver.findElementByXPath(unLike);
        unLikeBtn.click();
    }

    @Test(priority = 209)
    void sharePost(){
        String share = "//android.widget.TextView[@content-desc=\"Share Button\"]";

        MobileElement shareBtn = driver.findElementByXPath(share);
        shareBtn.click();
    }

    @Test(priority = 210)
    void shareToHistory(){
        String share = "//android.view.ViewGroup[@content-desc=\"Share to Your Story\"]";

        MobileElement btn = driver.findElementByXPath(share);
        btn.click();
    }

    @Test(priority = 211)
    void cancel(){
        String cancel = "//android.widget.Button[@content-desc=\"Discard photo\"]/android.widget.ImageView";

        MobileElement btn = driver.findElementByXPath(cancel);
        btn.click();
    }

    @Test(priority = 212)
    void confirm(){
        String cancel = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.Button[2]";

        MobileElement btn = driver.findElementByXPath(cancel);
        btn.click();
    }

    @Test(priority = 213)
    void back() throws InterruptedException {
        String back = "//android.widget.ImageView[@content-desc=\"Back\"]";

        MobileElement btn = driver.findElementByXPath(back);
        btn.click();
        btn.click();
        btn = driver.findElementByXPath(back);
        btn.click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Liked Pages\"]");
        btn.click();
    }
}
