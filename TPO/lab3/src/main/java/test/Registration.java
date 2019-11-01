package test;


import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.SkipException;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertTrue;

public class Registration extends BaseConfiguration {
    @Test(priority = 1)
    void closeDialogWindowWithOtherAccount() {
        String closeBtnPath = "com.google.android.gms:id/cancel";

        MobileElement closeBtn = driver.findElementById(closeBtnPath);
        closeBtn.click();
    }

    @Test(priority = 2)
    void clickOnCreateNewFacebookAccountBtn() {
        String registrationBtnPath = "Create New Facebook Account";

        MobileElement registrationBtn = driver.findElementByAccessibilityId(registrationBtnPath);
        registrationBtn.click();
    }

    @Test(priority = 3)
    void clickOnNextBtnInJoinFacebookPage() {
        String nextBtnPath = "android.widget.Button";

        MobileElement nextBtn = driver.findElement(By.className(nextBtnPath));
        nextBtn.click();
    }

    @Test(priority = 4)
    void selectGooglePlayAccount() {
        String selectionAccPnlPath = "//android.widget.LinearLayout[@content-desc=\"Choose an Account\"]";
        String accountBtnPath = "//android.widget.LinearLayout[@content-desc=\"Dmitriy Kurnikov\"]/" +
                "android.widget.LinearLayout/android.widget.LinearLayout";

        MobileElement selectionAccPnl = driver.findElement(By.xpath(selectionAccPnlPath));

        MobileElement closeBtn = driver.findElementByXPath(accountBtnPath);
        closeBtn.click();

//        assertTrue(selectionAccPnl.isDisplayed());
    }

    @Test(priority = 5)
    void tryToGoNextAfterFillFields() {
        String nextBtnPth = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
                "FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
                "android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button";

        MobileElement nextBtn = driver.findElementByXPath(nextBtnPth);
        nextBtn.click();
    }

    @Test(priority = 6)
    void selectMonthOfBirth() {
        String monthInputPathPickers = "//android.widget.LinearLayout[@resource-id=\"android:id/pickers\"]/" +
                "android.widget.NumberPicker[1]/android.widget.Button[2]";
        String monthInputPathSwitch = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout" +
                "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout" +
                "/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.Button";
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try{
            MobileElement monthInput = driver.findElement(By.xpath(monthInputPathSwitch));
            monthInput.click();
            monthInput = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout" +
                    "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView" +
                    "/android.widget.CheckedTextView[10]"));
            monthInput.click();
        }catch (Exception e){
            MobileElement monthInput = driver.findElement(By.xpath(monthInputPathPickers));
            monthInput.click();
        }

    }

    @Test(priority = 7)
    void selectDayOfBirth() {
        String dayInputPath = "//android.widget.LinearLayout[@resource-id=\"android:id/pickers\"]/" +
                "android.widget.NumberPicker[2]/android.widget.Button[1]";
        String dayInputPathSwitch = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout" +
                "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout" +
                "/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.Button";

        try {

            MobileElement dayInput = driver.findElement(By.xpath(dayInputPathSwitch));
            dayInput.click();
            dayInput = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout" +
                    "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView" +
                    "/android.widget.CheckedTextView[12]"));
            dayInput.click();
        }catch (Exception e){
            MobileElement dayInput = driver.findElement(By.xpath(dayInputPath));
            dayInput.click();
        }

    }

    @Test(priority = 8)
    void selectYearOfBirth() {
        String yearInputPath = "//android.widget.LinearLayout[@resource-id=\"android:id/pickers\"]/" +
                "android.widget.NumberPicker[3]/android.widget.Button[1]";
        String yearInputPathSwitch = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout" +
                "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout" +
                "/android.widget.LinearLayout[2]/android.widget.LinearLayout[3]/android.widget.Button";
        try {
            MobileElement yearInput = driver.findElement(By.xpath(yearInputPathSwitch));
            yearInput.click();
            yearInput = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout" +
                    "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView" +
                    "/android.widget.CheckedTextView[1]"));
            yearInput.click();
        }catch (Exception e) {
            MobileElement yearInput = driver.findElement(By.xpath(yearInputPath));
            if (yearInput.getText().equals("2017"))
                for (int i = 0; i < 21; i++)
                    yearInput.click();
        }

    }

    @Test(priority = 9)
    void tryToGoNextAfterSelectionBirthday() {
        String nextBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
                "FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
                "android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button";

        MobileElement nextBtn = driver.findElement(By.xpath(nextBtnPath));
        nextBtn.click();

    }

//    @Test(priority = 10)
//    void confirmBirthday() {
//        String dialogPnlPath = "android:id/content";
//        String yesBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget." +
//                "FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout" +
//                "/android.widget.Button[2]";
//
//        MobileElement dialogPnl = driver.findElement(By.id(dialogPnlPath));
//
//        MobileElement yesBtn = driver.findElement(By.xpath(yesBtnPath));
//        yesBtn.click();
//        assertTrue(dialogPnl.isDisplayed());
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//    }

    @Test(priority = 11)
    void selectGender() {
        String genderRadiobuttonPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout" +
                "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[2]";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        MobileElement genderRadiobutton = driver.findElement(By.xpath(genderRadiobuttonPath));
        genderRadiobutton.click();
    }

    @Test(priority = 12)
    void tryToGoNextAfterSelectionGender() {
        String nextBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
                "FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
                "android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button";

        MobileElement nextBtn = driver.findElement(By.xpath(nextBtnPath));
        nextBtn.click();
    }

    @Test(priority = 13)
    void enterMobileNumber() {
        String nextBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/" +
                "android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
                "android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button";
        String textField = "android.widget.EditText";
        MobileElement text = driver.findElementByClassName(textField);
        text.clear();
        text.sendKeys("89190008770");
        MobileElement nextBtn = driver.findElement(By.xpath(nextBtnPath));
        nextBtn.click();
    }

    @Test(priority = 14)
    void inputPassword() {
        String passwordFieldPath = "//android.widget.EditText[@resource-id=\"com.facebook.katana:id/(name removed)\"]";

        MobileElement passwordField = driver.findElement(By.xpath(passwordFieldPath));
        passwordField.click();
        passwordField.sendKeys("2red39iphone16trklnk2");
    }

    @Test(priority = 15)
    void tryToGoNextAfterInputPassword() {
        String nextBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
                "FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
                "android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button";

        MobileElement nextBtn = driver.findElement(By.xpath(nextBtnPath));
        nextBtn.click();
    }

    @Test(priority = 16)
    void singUpWithoutUploadingContacts() {
        MobileElement nextBtn = driver.findElementByAccessibilityId("Sign up without uploading my contacts");
        nextBtn.click();
    }

    @Test(priority = 17)
    void closeDialogWindowAboutRemindPassword() {

        String yesBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.Button[2]\n";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        MobileElement yesBtn = driver.findElementByXPath(yesBtnPath);
        yesBtn.click();
    }

    @Test(priority = 18)
    void rememberPhoneNumberAndPassword() {
        String okBtnStr = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[2]";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        MobileElement okBtn = driver.findElementByXPath(okBtnStr);
        okBtn.click();
    }

    @Test(priority = 19)
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

    @Test(priority = 20)
    void removeAccountFromDevice() {
        MobileElement el1 = driver.findElementByAccessibilityId("Menu");
        el1.click();

        String removeBtnPath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget." +
                "ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/" +
                "android.widget.TextView";
        MobileElement removeBtn = driver.findElementByXPath(removeBtnPath);
        removeBtn.click();
    }

    @Test(priority = 21)
    void confirmToRemove(){
        String btn = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.Button[2]\n";

        MobileElement confirmBtn = driver.findElementByXPath(btn);
        confirmBtn.click();
    }
}
