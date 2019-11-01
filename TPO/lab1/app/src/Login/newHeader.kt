package Login

import Click
import Get
import ResultString
import Script
import Wait
import action
import resultList

class newHeader{
    data class header(
        val name: String,
        val script: List<action>
    )
    private val scriptList: List<header> = listOf(
        header("logo",
            listOf(
                action(Click(), "//*[@class=\"logo-anchor\"]")
            )),
        header("search",
            listOf(
                action(Click(),"//*[@class=\"search_form_field\"]"),
                action(Wait(),"//*[@class=\"tracked_tags tag search_results_section\"]")
            )),
        header("search -> list element 1",
            listOf(
                action(Click(), "//*[@class=\"search_form_field\"]"),
                action(Wait(), "//*[@class=\"tracked_tags tag search_results_section\"]"),
                action(Click(), "//*[@class=\"tracked_tags tag search_results_section\"]/div[1]/div[1]/a"),
                action(Wait(),"//*[@class=\"blogs_wrapper_carousel_inner\"]")
            )),
        header("search -> list element 2",
            listOf(
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Click(), "//*[@class=\"search_form_field\"]"),
                action(Wait(), "//*[@class=\"tracked_tags tag search_results_section\"]"),
                action(Click(), "//*[@class=\"tracked_tags tag search_results_section\"]/div[2]/div[1]/a"),
                action(Wait(),"//*[@class=\"blogs_wrapper_carousel_inner\"]")
            )),
        header("search -> list element 3",
            listOf(
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Click(), "//*[@class=\"search_form_field\"]"),
                action(Wait(), "//*[@class=\"tracked_tags tag search_results_section\"]"),
                action(Click(), "//*[@class=\"tracked_tags tag search_results_section\"]/div[3]/div[1]/a"),
                action(Wait(),"//*[@class=\"blogs_wrapper_carousel_inner\"]")
            )),
//        header("search -> list element 4",
//            listOf(
//                action(Get(),"https://www.tumblr.com/dashboard"),
//                action(Click(), "//*[@class=\"search_form_field\"]"),
//                action(Wait(), "//*[@class=\"tracked_tags tag search_results_section\"]"),
//                action(Click(), "//*[@class=\"tracked_tags tag search_results_section\"]/div[4]/div[1]/a"),
//                action(Wait(),"//*[@class=\"blogs_wrapper_carousel_inner\"]")
//            )),
//        header("search -> list element 5",
//            listOf(
//                action(Get(),"https://www.tumblr.com/dashboard"),
//                action(Click(), "//*[@class=\"search_form_field\"]"),
//                action(Wait(), "//*[@class=\"tracked_tags tag search_results_section\"]"),
//                action(Click(), "//*[@class=\"tracked_tags tag search_results_section\"]/div[5]/div[1]/a"),
//                action(Wait(),"//*[@class=\"blogs_wrapper_carousel_inner\"]")
//            )),
//        header("search -> list element 6",
//            listOf(
//                action(Get(),"https://www.tumblr.com/dashboard"),
//                action(Click(), "//*[@class=\"search_form_field\"]"),
//                action(Wait(), "//*[@class=\"tracked_tags tag search_results_section\"]"),
//                action(Click(), "//*[@class=\"tracked_tags tag search_results_section\"]/div[6]/div[1]/a"),
//                action(Wait(),"//*[@class=\"blogs_wrapper_carousel_inner\"]")
//            )),
//        header("search -> list element 7",
//            listOf(
//                action(Get(),"https://www.tumblr.com/dashboard"),
//                action(Click(), "//*[@class=\"search_form_field\"]"),
//                action(Wait(), "//*[@class=\"tracked_tags tag search_results_section\"]"),
//                action(Click(), "//*[@class=\"tracked_tags tag search_results_section\"]/div[7]/div[1]/a"),
//                action(Wait(),"//*[@class=\"blogs_wrapper_carousel_inner\"]")
//            )),
//        header("search -> list element 8",
//            listOf(
//                action(Get(),"https://www.tumblr.com/dashboard"),
//                action(Click(), "//*[@class=\"search_form_field\"]"),
//                action(Wait(), "//*[@class=\"tracked_tags tag search_results_section\"]"),
//                action(Click(), "//*[@class=\"tracked_tags tag search_results_section\"]/div[8]/div[1]/a"),
//                action(Wait(),"//*[@class=\"blogs_wrapper_carousel_inner\"]")
//            )),
//        header("search -> list element 9",
//            listOf(
//                action(Get(),"https://www.tumblr.com/dashboard"),
//                action(Click(), "//*[@class=\"search_form_field\"]"),
//                action(Wait(), "//*[@class=\"tracked_tags tag search_results_section\"]"),
//                action(Click(), "//*[@class=\"tracked_tags tag search_results_section\"]/div[9]/div[1]/a"),
//                action(Wait(),"//*[@class=\"blogs_wrapper_carousel_inner\"]")
//            )),
//        header("search -> list element 10",
//            listOf(
//                action(Get(),"https://www.tumblr.com/dashboard"),
//                action(Click(), "//*[@class=\"search_form_field\"]"),
//                action(Wait(), "//*[@class=\"tracked_tags tag search_results_section\"]"),
//                action(Click(), "//*[@class=\"tracked_tags tag search_results_section\"]/div[10]/div[1]/a"),
//                action(Wait(),"//*[@class=\"blogs_wrapper_carousel_inner\"]")
//            )),
//        header("search -> list element 11",
//            listOf(
//                action(Get(),"https://www.tumblr.com/dashboard"),
//                action(Click(), "//*[@class=\"search_form_field\"]"),
//                action(Wait(), "//*[@class=\"tracked_tags tag search_results_section\"]"),
//                action(Click(), "//*[@class=\"tracked_tags tag search_results_section\"]/div[11]/div[1]/a"),
//                action(Wait(),"//*[@class=\"blogs_wrapper_carousel_inner\"]")
//            )),
        header("home button",
            listOf(
                action(Click(),"//*[@id=\"home_button\"]")
            )),
        header("discover button",
            listOf(
                action(Click(),"//*[@id=\"discover_button\"]"),
                action(Wait(),"//*[@id=\"discover_actions_discover\"]")
            )),
        header("inbox button",
            listOf(
                action(Click(),"//*[@id=\"inbox_button\"]"),
                action(Wait(),"//*[@class=\"controls_section_item selected\"]")
            )),
        header("messaging button",
            listOf(
                action(Click(),"//*[@id=\"messaging_button\"]"),
                action(Wait(),"//*[@class=\"compose-toggle compose-start\"]")
            )),
        header("activity button",
            listOf(
                action(Click(),"//*[@id=\"activity_button\"]"),
                action(Wait(),"//*[@class=\"activity-popover-header\"]"),
                action(Get(),"https://www.tumblr.com/dashboard")
            )),
        header("account button",
            listOf(
                action(Click(),"//*[@id=\"account_button\"]"),
                action(Wait(), "//*[@class=\"popover_inner_list\"]"),
                action(Get(),"https://www.tumblr.com/dashboard")
            )),
        header("account button -> likes",
            listOf(
                action(Click(),"//*[@id=\"account_button\"]"),
                action(Wait(), "//*[@class=\"popover_inner_list\"]"),
                action(Click(),"//*[@href=\"/likes\"]"),
                action(Wait(),"//*[@class=\"open_blog_link\"]")
            )),
        header("account button -> settings",
            listOf(
                action(Click(),"//*[@id=\"account_button\"]"),
                action(Wait(), "//*[@class=\"popover_inner_list\"]"),
                action(Click(),"//*[@href=\"/settings\"]"),
                action(Wait(),"//*[@class=\"settings_heading\"]")
            )),
        header("account button -> help",
            listOf(
                action(Click(),"//*[@id=\"account_button\"]"),
                action(Wait(), "//*[@class=\"popover_inner_list\"]"),
                action(Click(),"//*[@href=\"/help\"]"),
                action(Wait(),"//*[@class=\"hero-inner\"]"),
                action(Get(),"https://www.tumblr.com/dashboard")
            )),
        header("account button -> blog",
            listOf(
                action(Click(),"//*[@id=\"account_button\"]"),
                action(Wait(), "//*[@class=\"popover_inner_list\"]"),
                action(Click(),"//*[@class=\"blog-list-item blog-list-item--on-light \"]"),
                action(Wait(),"//*[@class=\"open_blog_link\"]")
            )),
        header("account button -> followers",
            listOf(
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Click(),"//*[@id=\"account_button\"]"),
                action(Wait(), "//*[@class=\"popover_inner_list\"]"),
                action(Click(),"//*[@href=\"/blog/santaclown6/followers\"]"),
                action(Wait(),"//*[@class=\"title_and_controls\"]")
            )),
        header("account button -> activity",
            listOf(
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Click(),"//*[@id=\"account_button\"]"),
                action(Wait(), "//*[@class=\"popover_inner_list\"]"),
                action(Click(),"//*[@href=\"/blog/santaclown6/activity\"]")
            )),
        header("account button -> review",
            listOf(
                action(Click(),"//*[@id=\"account_button\"]"),
                action(Wait(), "//*[@class=\"popover_inner_list\"]"),
                action(Click(),"//*[@href=\"/blog/santaclown6/review\"]"),
                action(Wait(),"//*[@class=\"currently_selected_blog hide_overflow blog_title\"]")
            )),
        header("account button -> blog settings",
            listOf(
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Click(),"//*[@id=\"account_button\"]"),
                action(Wait(), "//*[@class=\"popover_inner_list\"]"),
                action(Click(),"//*[@href=\"/settings/blog/santaclown6\"]"),
                action(Wait(),"//*[@class=\"oauth_group is_twitter \"]")
            )),
        header("compose post",
            listOf(
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Click(),"//*[@class=\"compose-button\"]"),
                action(Wait(), "//*[@class=\"post-type-icon icon-text\"]"),
                action(Get(),"https://www.tumblr.com/dashboard")
            )),
        header("compose post -> click on create post",
            listOf(
                action(Click(),"//*[@class=\"compose-button\"]"),
                action(Wait(), "//*[@class=\"post-type-icon icon-text\"]"),
                action(Click(),"//*[@class=\"post-type-icon icon-text\"]"),
                action(Wait(),"//*[@class=\"post-form--header clearfix\"]"),
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Wait(),"//*[@class=\"compose-button\"]")
            )),
        header("compose post -> click on create photo",
            listOf(
                action(Wait(),"//*[@class=\"compose-button\"]"),
                action(Click(),"//*[@class=\"compose-button\"]"),
                action(Wait(), "//*[@class=\"post-type-icon icon-photo\"]"),
                action(Click(),"//*[@class=\"post-type-icon icon-photo\"]"),
                action(Wait(),"//*[@class=\"split-cells\"]"),
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Wait(),"//*[@class=\"compose-button\"]")
            )),
        header("compose post -> click on create quote",
            listOf(
                action(Wait(),"//*[@class=\"compose-button\"]"),
                action(Click(),"//*[@class=\"compose-button\"]"),
                action(Wait(), "//*[@class=\"post-type-icon icon-quote\"]"),
                action(Click(),"//*[@class=\"post-type-icon icon-quote\"]"),
                action(Wait(),"//*[@class=\"quote-field extra_large\"]"),
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Wait(),"//*[@class=\"compose-button\"]")
            )),
        header("compose post -> click on create link",
            listOf(
                action(Wait(),"//*[@class=\"compose-button\"]"),
                action(Click(),"//*[@class=\"compose-button\"]"),
                action(Wait(), "//*[@class=\"post-type-icon icon-link\"]"),
                action(Click(),"//*[@class=\"post-type-icon icon-link\"]"),
                action(Wait(),"//*[@class=\"link-editor\"]"),
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Wait(),"//*[@class=\"compose-button\"]")
            )),
        header("compose post -> click on create chat",
            listOf(
                action(Wait(),"//*[@class=\"compose-button\"]"),
                action(Click(),"//*[@class=\"compose-button\"]"),
                action(Wait(), "//*[@class=\"post-type-icon icon-chat\"]"),
                action(Click(),"//*[@class=\"post-type-icon icon-chat\"]"),
                action(Wait(),"//*[@class=\"chat-field normal\"]"),
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Wait(),"//*[@class=\"compose-button\"]")
            )),
        header("compose post -> click on create audio",
            listOf(
                action(Wait(),"//*[@class=\"compose-button\"]"),
                action(Click(),"//*[@class=\"compose-button\"]"),
                action(Wait(), "//*[@class=\"post-type-icon icon-audio\"]"),
                action(Click(),"//*[@class=\"post-type-icon icon-audio\"]"),
                action(Wait(),"//*[@class=\"audio-search-field\"]"),
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Wait(),"//*[@class=\"compose-button\"]")
            )),
        header("compose post -> click on create video",
            listOf(
                action(Wait(),"//*[@class=\"compose-button\"]"),
                action(Click(),"//*[@class=\"compose-button\"]"),
                action(Wait(), "//*[@class=\"post-type-icon icon-video\"]"),
                action(Click(),"//*[@class=\"post-type-icon icon-video\"]"),
                action(Wait(),"//*[@class=\"media-upload\"]"),
                action(Get(),"https://www.tumblr.com/dashboard"),
                action(Wait(),"//*[@class=\"compose-button\"]")
            ))

    )

    fun init(){
        resultList.add(ResultString("INFO", "Header"))

        scriptList.forEach { script ->
            resultList.add(Script(script.script, script.name, "success click on ${script.name}"))
        }
    }
}