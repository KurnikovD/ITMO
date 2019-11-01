package Login

import Click
import ClickX
import ResultString
import Script
import SendKeys
import Wait
import action
import org.openqa.selenium.By
import resultList
import webDriver

class Login {

    private val actionsList: List<action> = listOf(
        action(Click(), "//*[@class=\"chrome big signup_forms_submit touchy dark\"]"),
        action(SendKeys(), "//*[@id=\"signup_determine_email\"]", 0, "kurnikov.dmitriy@ya.ru"),
        action(Click(), "//*[@class=\"signup_determine_btn active\"]"),
        action(Click(), "//*[@class=\"magiclink_password_container chrome\"]"),
        action(SendKeys(), "//*[@id=\"signup_password\"]", 0, "DFGHJUJM1234"),
        action(Click(), "//*[@class=\"signup_login_btn active\"]"),
        action(Wait(), "//*[@id=\"post_controls_avatar\"]")
    )

    fun init(){
        resultList.add(ResultString("INFO", "Login"))
        webDriver.get("https://www.tumblr.com/")
        resultList.add(Script(actionsList, "Login", "login success"))

    }
}