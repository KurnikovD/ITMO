import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

fun Script(actions: List<action>, name: String, description:String): ResultString {

    for (act in actions){
        val result: List<String> = act.type.init(act).split('/')
        if (result[0] == "not")
            return ResultString("FALSE", name, "could not ${result[1]} element ${act.element}")
    }
    return ResultString("DONE", name, description)
}

//private val wait = FluentWait<WebDriver>(webDriver)
//                        .withMessage("Element was not found")
//                        .withTimeout(Duration.ofSeconds(6))
//                        .pollingEvery(Duration.ofMillis(50))

private val wait = WebDriverWait(webDriver, 10)

interface Actions{
    fun init(act: action): String
}

class Click : Actions {
    override fun init(act: action): String =
        try{
            wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath(act.element))))
            webDriver.findElement(By.xpath(act.element)).click()

            "done/click on"
        }catch(e:Exception){
            "not/click on"
        }
}
//*[@
class ClickX : Actions{
    override fun init(act: action): String =
        try{
           wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(act.element)))
            webDriver.findElements(By.xpath(act.element))[act.x].click()
            "done/click on"
        }catch(e:Exception){
            "not/click on"
        }
}

class SendKeys : Actions{
    override fun init(act: action): String =
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(act.element)))
            webDriver.findElement(By.xpath(act.element)).sendKeys(act.string)

            "done/find"
        }catch(e:Exception){
            "not/find"
        }
}

class Wait : Actions {
    override fun init(act: action): String =
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(act.element)))
            "done/find"
        }catch(e:Exception){
            "not/find"
        }
}

class JS : Actions {
    override fun init(act: action): String =
        try{
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(act.element)))
            (webDriver as JavascriptExecutor).executeScript("arguments[0].click();", webDriver.findElement(By.xpath(act.element)))
            "done/click on"
        }catch(e:Exception){
            "not/click on"
        }
}

class Clear: Actions{
    override fun init(act: action): String =
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(act.element)))
            webDriver.findElement(By.xpath(act.element)).clear()
            "done/find"
        }catch(e:Exception){
            "not/find"
        }
}

class Scroll: Actions{
    override fun init(act: action): String {
        (webDriver as JavascriptExecutor).executeScript("window.scrollBy(0,250)")
        return "done/scroll"
    }
}
class Get: Actions{
    override fun init(act: action): String {
        webDriver.get(act.element)
        return "done/go to"
    }
}
class Refresh: Actions{
    override fun init(act: action): String {
        webDriver.navigate().refresh()
        return "done/refresh"
    }
}

class Close:Actions {
    override fun init(act: action): String {
        webDriver.close()
        return "done/close"
    }
}

class URL:Actions {
    override fun init(act: action): String {
        if (webDriver.currentUrl == act.element)
            return "done/url"
        else
            return "not/load url"
    }
}

class URLStart:Actions {
    override fun init(act: action): String {
        if (webDriver.currentUrl.startsWith(act.element))
            return "done/url"
        else
            return "not/load url"
    }

}