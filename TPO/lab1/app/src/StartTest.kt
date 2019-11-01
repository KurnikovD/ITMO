

import Login.RunTest
import Login.Search
import Logout.MainPage
import Logout.Registration
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

val webDriver : WebDriver = ChromeDriver(ChromeOptions().addArguments("--start-maximized"))
val resultList: MutableList<ResultString> = mutableListOf()

const val ANSI_GREEN = "\u001B[92m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_RESET = "\u001B[0m"

fun main() {
    webDriver.get("https://www.tumblr.com/")
    webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    Registration().init()
    RunTest().init()
    var good = 0
    var bad = 0
    for (message in resultList)
        when (message.exception) {
            "DONE" -> {
                println("\t${ANSI_GREEN}${message.methodName}${ANSI_RESET} - ${message.description}")
                good++
            }
            "INFO" -> println(message.methodName)
            "FALSE" -> {
                println("\t${ANSI_RED}${message.methodName}${ANSI_RESET} - ${message.description}")
                bad++
            }
        }

    println("ResultTest:\nOK: $good | ERR: $bad")
    webDriver.quit()

}