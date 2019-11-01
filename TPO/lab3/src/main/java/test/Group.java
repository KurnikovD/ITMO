package test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.annotations.Test;

public class Group extends BaseConfiguration {

    @Test(priority = 401)
    void goToTheGroupPage(){
        String mainMenu = "//android.view.View[@content-desc=\"Menu, Tab 5 of 5\"]";
        String groupMenu = "//android.view.ViewGroup[@content-desc=\"Groups\"]";

        MobileElement mainMenuBtn = driver.findElementByXPath(mainMenu);
        mainMenuBtn.click();

        MobileElement groupMenuBtn = driver.findElementByXPath(groupMenu);
        groupMenuBtn.click();
    }

    @Test(priority = 402)
    void openSearchGroups() {
        String searchStr = "//android.widget.ImageView[@content-desc=\"Search\"]";

        MobileElement searchBtn = driver.findElementByXPath(searchStr);
        searchBtn.click();
    }

    @Test(priority = 403)
    void searchGroup() {
        (new TouchAction(driver)).tap(PointOption.point(387, 95)).perform();
        String searchInputPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget" +
                ".FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
                "android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
                ".LinearLayout/android.widget.EditText";

        MobileElement searchInput = driver.findElementByXPath(searchInputPath);
        searchInput.click();
        searchInput.sendKeys("Cats\n");
//        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.ENTER);
    }

    @Test(priority = 404)
    void selectSearch() {
        String seachSelectStr = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView" +
                "/android.view.ViewGroup[2]";

        MobileElement selectSearchBtn = driver.findElementByXPath(seachSelectStr);
        selectSearchBtn.click();
    }

    @Test(priority = 406)
    void openGroup() {
        String groupPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup" +
                "/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup";

        MobileElement group = driver.findElementByXPath(groupPath);
        group.click();
    }

    @Test(priority = 407)
    void joinGroup() {
        MobileElement joinGroupBtn = driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Join Group\"]");
        joinGroupBtn.click();
    }

    @Test(priority = 408)
    void cancelRequest() {
        MobileElement cancelRequestBtn = driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Cancel Request\"]");
        cancelRequestBtn.click();
    }

    @Test(priority = 409)
    void openGroupTools(){
        (new TouchAction(driver)).tap(PointOption.point(1350, 175)).perform();
    }


    @Test(priority = 410)
    void clickOnShareGroupBtn() {
        String shareLink = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup" +
                "/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]" +
                "/android.view.ViewGroup[1]";

        MobileElement shareBtn = driver.findElementByXPath(shareLink);
        shareBtn.click();
    }

    @Test(priority = 411)
    void selectFacebookToShareGroup() {
        String facebookBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android" +
                ".widget.FrameLayout/android.widget.ScrollView/android.widget.ListView/android.widget.LinearLayout/" +
                "android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.ImageView";

        MobileElement facebookBtn = driver.findElementByXPath(facebookBtnPath);
        facebookBtn.click();
    }

    @Test(priority = 412)
    void printMessege(){
        String writeElement ="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout" +
                "/android.widget.EditText";

        MobileElement element = driver.findElementByXPath(writeElement);
        element.sendKeys("COOOOOOOOOOOL!");
    }

    @Test(priority = 413)
    void createPost() {
        MobileElement createPostBtn = driver.findElementByXPath("//android.widget.Button[@content-desc=\"POST\"]");
        createPostBtn.click();
    }

    @Test(priority = 414)
    void back() throws InterruptedException {
        (new TouchAction(driver)).tap(PointOption.point(100, 170)).perform();
        MobileElement backBtn = driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"Back\"]");
        backBtn.click();
        Thread.sleep(1000);
        backBtn = driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"Back\"]");
        backBtn.click();
        Thread.sleep(1000);
        backBtn = driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"Back\"]");
        backBtn.click();
        Thread.sleep(1000);
        backBtn = driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"Back\"]");
        backBtn.click();
    }
}
