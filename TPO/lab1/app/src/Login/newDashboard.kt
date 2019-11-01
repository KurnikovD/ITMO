package Login

import Click
import ClickX
import Close
import Get
import JS
import Refresh
import ResultString
import Script
import Scroll
import SendKeys
import Wait
import action
import resultList
import webDriver

class newDashboard {
    private data class dashboard(
        val name:String,

        val script: List<action>
    )

    val xpathForPost = "//*[@id=\"posts\"]/li[2]/div[1]/div[2]"

    private val scriptList: List<dashboard> =listOf(
        dashboard("avatar",
            listOf(
                action(Click(),"//*[@id=\"post_controls_avatar\"]"),
                action(Wait(),"//*[@class=\"indash_header_wrapper  has_info tumblelog_name_santaclown6\"]")
            )
        ),
        dashboard("post",
            listOf(
                action(Click(),"//*[@class=\"icon_post_text\"]"),
                action(Wait(),"//*[@class=\"post-container-inner post\"]"),
                action(Get(),"https://www.tumblr.com/dashboard")
            )
        ),
        dashboard("photo",
            listOf(
                action(Click(),"//*[@class=\"icon_post_photo\"]"),
                action(Wait(),"//*[@class=\"post-container-inner post\"]")
            )
        ),
        dashboard("quote",
            listOf(
                action(Click(),"//*[@class=\"icon_post_quote\"]"),
                action(Wait(),"//*[@class=\"post-container-inner post\"]")
            )
        ),
        dashboard("link",
            listOf(
                action(Click(),"//*[@class=\"icon_post_link\"]"),
                action(Wait(),"//*[@class=\"post-container-inner post\"]"),
                action(Get(),"https://www.tumblr.com/dashboard")
            )
        ),
        dashboard("chat",
            listOf(
                action(Click(),"//*[@class=\"icon_post_chat\"]"),
                action(Wait(),"//*[@class=\"post-container-inner post\"]")
            )
        ),
        dashboard("audio",
            listOf(
                action(Click(),"//*[@class=\"icon_post_audio\"]"),
                action(Wait(),"//*[@class=\"post-container-inner post\"]")
            )
        ),
        dashboard("video",
            listOf(
                action(Click(),"//*[@class=\"icon_post_video\"]"),
                action(Wait(),"//*[@class=\"post-container-inner post\"]")
            )
        ),
        dashboard("radar",
            listOf(
                action(Click(),"//*[@class=\"radar_footer has_follow_button\"]"),
                action(Wait(),"//*[@class=\"indash_blog\"]")
            )
        ),
        dashboard("post link",
            listOf(
                action(Click(),"$xpathForPost/div[1]/div[1]/a"),
                action(Wait(),"//*[@class=\"drawer peepr-drawer-container open\"]")
            )
        ),
        dashboard("share -> report",
            listOf(
                action(Click(),"$xpathForPost/div[@class=\"post_footer clearfix\"]/div[2]/div[1]/div[1]"),
                action(Click(),"//*[@class=\"messaging-share-post-external-network network--flag\"]"),
                action(Wait(),"//*[@class=\"drawer open\"]")
            )
        ),
//        dashboard("share link",
//            listOf(
//                action(Click(),"$xpathForPost/div[3]/div[2]/div[1]/div[1]"),
//                action(Click(),"//*[@class=\"messaging-share-post-external-network network--permalink\"]"),
//                action(Get(),"https://www.tumblr.com/dashboard"),
//                action(Wait(),"//*[@class=\"logo\"]")
//            )
//        ),
        dashboard("share -> copy link",
            listOf(
                action(Click(),"$xpathForPost/div[@class=\"post_footer clearfix\"]/div[2]/div[1]/div[1]"),
                action(Click(), "//*[@class=\"messaging-share-post-external-network network--copy-permalink\"]"),
                action(JS(), "//*[@class=\"ui_button btn_0 chrome blue\"]")
            )
        ),
        dashboard("share -> input",
            listOf(
                action(Click(),"$xpathForPost/div[@class=\"post_footer clearfix\"]/div[2]/div[1]/div[1]"),
                action(Click(), "//*[@class=\"messaging-share-post-external-network network--embed\"]"),
                action(Wait(), "//*[@class=\"form-main\"]"),
                action(JS(), "//*[@class=\"back-button\"]")
            )
        ),
        dashboard("share -> e-mail",
            listOf(
                action(Click(),"$xpathForPost/div[@class=\"post_footer clearfix\"]/div[2]/div[1]/div[1]"),
                action(JS(), "//*[@class=\"messaging-share-post-external-network network--email\"]"),
                action(SendKeys(), "//*[@class=\"email input\"]",0,"kurnikov.dmitrii@gmail.com"),
                action(SendKeys(), "//*[@class=\"message textarea\"]",0,"text"),
                action(JS(), "//*[@class=\"button tx-button blue\"]"),
                action(Wait(), "//*[@class=\"form-result form-result--fail visible\"]")
            )
        ),
        dashboard("reply",
            listOf(
                action(Click(),"$xpathForPost/div[@class=\"post_footer clearfix\"]/div[2]/div[1]/div[2]"),
                action(Wait(), "//*[@class=\"post-activity can-reply\"]")
            )
        ),
        dashboard("reblog",
            listOf(
                action(Click(),"$xpathForPost/div[@class=\"post_footer clearfix\"]/div[2]/div[1]/a[1]"),
                action(JS(), "//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_avatar_link\"]"),
                action(Get(),"https://www.tumblr.com/blog/santaclown6"),
                action(Wait(),"//*[@class=\"post_container\"]"),
                action(Scroll(),""),
                action(Click(),"//*[@class=\"post_control post-control-icon post_control_menu creator\"]"),
                action(Click(),"//*[@class=\"post_control edit show_label\"]"),
                action(SendKeys(), "//*[@class=\"editor editor-richtext\"]",0,"Класс!!!"),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]"),
                action(Scroll(),""),
                action(Click(), "//*[@title=\"Параметры поста\"]"),
                action(Click(), "//*[@title=\"Удалить\"]"),
                action(JS(), "//*[@class=\"ui_button btn_1 chrome blue\"]"),
                action(Wait(),"//*[@class=\"posts only_me\"]")
            )
        ),
        dashboard("like",
            listOf(
                action(Click(),"$xpathForPost/div[@class=\"post_footer clearfix\"]/div[2]/div[1]/div[3]"),
                action(Wait(), "$xpathForPost/div[@class=\"post_footer clearfix\"]/div[2]/div[1]/div[@class=\"post_control post-control-icon like liked\"]")
            )
        ),
        dashboard("dislike",
            listOf(
                action(Click(),"$xpathForPost/div[@class=\"post_footer clearfix\"]/div[2]/div[1]/div[3]"),
                action(Wait(), "$xpathForPost/div[@class=\"post_footer clearfix\"]/div[2]/div[1]/div[@class=\"post_control post-control-icon like\"]")
            )
        )
    )

    fun init(){
        resultList.add(ResultString("INFO", "Dashboard"))

        scriptList.forEach { script ->
            webDriver.get("https://www.tumblr.com/dashboard")
            resultList.add(Script(script.script, script.name, "success click on ${script.name}"))
        }
    }
}