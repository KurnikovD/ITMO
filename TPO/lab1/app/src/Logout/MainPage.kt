package Logout

import Click
import Close
import ResultString
import Script
import URLStart
import Wait
import action
import resultList

class MainPage {

    data class mainPage(
        val name: String,
        val scripts: List<action>
    )

    val scriptList: List<mainPage> = listOf(
        mainPage("ios APP",
            listOf(
                action(Wait(), "//*[@class=\"app-link app-ios\"]")
            )),
        mainPage("android APP",
            listOf(
                action(Wait(), "//*[@class=\"app-link app-android\"]")
            ))
    )

    fun init(){
        resultList.add(ResultString("INFO", "Main Page"))
        scriptList.forEach{
            resultList.add(Script(it.scripts, it.name, "success click on element ${it.name}"))
        }
    }
}