package Login

import Click
import Get
import JS
import ResultString
import Script
import Wait
import action
import resultList

class Logout {

    private val actionList: List<action> = listOf(
        action(Get(),"https://www.tumblr.com/dashboard"),
        action(Wait(), "//*[@id=\"account_button\"]"),
        action(Click(),"//*[@id=\"account_button\"]"),
        action(Wait(),"//*[@href=\"/logout\"]"),
        action(Click(),"//*[@href=\"/logout\"]"),
        action(JS(), "//*[@class=\"ui_button btn_1 chrome blue\"]"),
        action(Wait(),"//*[@class=\"app-link app-android\"]")
    )

    fun init(){
        resultList.add(ResultString("INFO", "LogOut"))
        resultList.add(Script(actionList,"logout","logout success"))
    }
}