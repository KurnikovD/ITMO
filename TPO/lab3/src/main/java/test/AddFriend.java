package test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.annotations.Test;

public class AddFriend extends BaseConfiguration {

    @Test(priority = 301)
    void openFriendPage(){
        String friendPage = "//android.view.View[@content-desc=\"Friends, Tab 2 of 5\"]";

        MobileElement element = driver.findElementByXPath(friendPage);
        element.click();
    }

    @Test(priority = 302)
    void openSearchFriends() {
        String str = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]";
        driver.findElementByXPath(str);
        (new TouchAction(driver)).tap(PointOption.point(1350, 350)).perform();
    }

    @Test(priority = 303)
    void searchFriends() {
        String searchInputPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText";

        MobileElement searchInput = driver.findElementByXPath(searchInputPath);
        searchInput.sendKeys("Kurnikov Dmitriy");
        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.ENTER);
    }

//    @Test(priority = 204)
//    void selectRecentSearch() {
//        String selectStr = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
//                "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
//                "/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView" +
//                "/android.view.ViewGroup[2]";
//
//        MobileElement recentSearch = driver.findElementByXPath(selectStr);
//        recentSearch.click();
//    }

    @Test(priority = 305)
    void choosePerson() {
        MobileElement person = driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\"Dmitriy Kurnikov\"])[2]");
        person.click();
    }

    @Test(priority = 306)
    void addFriend() {
        String addFriendBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup" +
                "/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[4]/android.view.ViewGroup/android.view.ViewGroup[1]\n";

        MobileElement addFriend = driver.findElementByXPath(addFriendBtnPath);
        addFriend.click();
    }

    @Test(priority = 307)
    void cancelAddNewFriends() {
        MobileElement el2 = driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"Close\"]");
        el2.click();
    }

    @Test(priority = 308)
    void cancelRequest() {
        String cancelBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup" +
                "/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[4]/android.view.ViewGroup/android.view.ViewGroup[1]\n";

        MobileElement cancelBtn = driver.findElementByXPath(cancelBtnPath);
        cancelBtn.click();
    }

    @Test(priority = 309)
    void confirmCancelRequest() {
        String confirmBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget" +
                ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/" +
                "android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/" +
                "android.view.ViewGroup[1]";

        MobileElement confirmBtn = driver.findElementByXPath(confirmBtnPath);
        confirmBtn.click();
    }

    @Test(priority = 310)
    void tryToGoBack() {
        MobileElement backBtn = driver.findElementByAccessibilityId("Back");
        backBtn.click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"People Heading\"]");
        backBtn.click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Recent Searches Heading\"]");
        backBtn.click();
    }
}
