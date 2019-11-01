package Logout

import Click
import ResultString
import Script
import SendKeys
import Wait
import action
import org.openqa.selenium.By
import resultList
import webDriver

class Registration {

    private val actionsList: List<action> = listOf(
        action(Click(), "//*[@class=\"chrome big signup_forms_submit touchy blue\"]"),
        action(SendKeys(), "//*[@id=\"signup_email\"]", 0, "Kurnikov.dmitriy@ya.ru"),
        action(SendKeys(), "//*[@id=\"signup_password\"]", 0, "kakoytoparol"),
        action(SendKeys(), "//*[@id=\"signup_username\"]", 0, "santa"),
        action(Click(), "//*[@class=\"signup_account_btn active\"]"),
        action(Wait(), "//*[@class=\"error\"]")

    )

    fun init() {
        resultList.add(ResultString("INFO", "Registration"))
        webDriver.get("https://www.tumblr.com/")
        val element = webDriver.findElement(By.xpath("//*[@id=\"account_actions_logged_out_dashboard\"]"))
        resultList.add(Script(actionsList, "Registration", "registration success"))
    }

}