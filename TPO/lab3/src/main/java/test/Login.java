package test;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Login extends BaseConfiguration {

    @Test(priority = 101)
    void closeDialogWindowWithOtherAccount() {
        String closeBtnPath = "//android.widget.LinearLayout[@content-desc=\"Choose a Credential\"]/" +
                "android.widget.LinearLayout/android.widget.Button";

        MobileElement closeBtn = driver.findElement(By.xpath(closeBtnPath));
        closeBtn.click();
    }

    @Test(priority = 102)
    void inputPhoneNumber() {
        MobileElement phoneNumberInput = driver.findElementByAccessibilityId("Username");
        phoneNumberInput.sendKeys("89190008770");
    }

    @Test(priority = 103)
    void inputPassword() {
        MobileElement passwordInput = driver.findElementByAccessibilityId("Password");
        passwordInput.sendKeys("2red39iphone16trklnk2");
    }

    @Test(priority = 104)
    void clickOnLogInBtn() {
        MobileElement logInBtn = driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Login\"]");
        logInBtn.click();
    }

    @Test(priority = 105)
    void notSaveLogInInfo() {
        String notNowBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/" +
                "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/" +
                "android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]";

        MobileElement notNowBtn = driver.findElementByXPath(notNowBtnPath);
        notNowBtn.click();
    }
}
