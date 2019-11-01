package test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseConfiguration {
    static AndroidDriver<MobileElement> driver;
    int timeOut = 30;
    static WebDriverWait wait;

    @BeforeSuite
    static void config() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S10");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("appActivity", "com.facebook.account.login.activity.SimpleLoginActivity");
        capabilities.setCapability("appPackage", "com.facebook.katana");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 50);
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
    }



    @AfterSuite
    public void closeBrowser() {
        driver.quit();
    }
}
