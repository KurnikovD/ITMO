package Login

import Click
import Get
import JS
import ResultString
import Script
import SendKeys
import URL
import Wait
import action
import resultList
import webDriver

class Search {

    data class search(
        val name: String,
        val scripts: List<action>
    )

    val scriptList: List<search> = listOf(
        search(
            "search",
            listOf(
                action(Click(), "//*[@id=\"search_query\"]"),
                action(SendKeys(), "//*[@id=\"search_query\"]", 0, "Cats\n"),
                action(Wait(), "//*[@class=\"search_term_heading\"]")
            )
        ),
        search(
            "carousel",
            listOf(
                action(Click(), "//*[@class=\"search_blogs_row \"]/div[1]"),
                action(Wait(), "//*[@class=\"drawer peepr-drawer-container open\"]"),
                action(Get(), "https://www.tumblr.com/search/cat")
            )
        ),
        search(
            "filter populate new",
            listOf(
                action(Wait(), "//*[@class=\"search_control_items\"]"),
                action(Click(), "//*[@class=\"search_control_items\"]/div[1]"),
                action(Wait(), "//*[@data-search-action-value=\"recent\"]"),
                action(Click(), "//*[@data-search-action-value=\"recent\"]"),
                action(Wait(), "//*[@class=\"search-posts-container\"]")
            )
        ),
        search(
            "filter populate top",
            listOf(
                action(Wait(), "//*[@class=\"search_control_items\"]"),
                action(Click(), "//*[@class=\"search_control_items\"]/div[1]"),
                action(Wait(), "//*[@data-search-action-value=\"top\"]"),
                action(Click(), "//*[@data-search-action-value=\"top\"]"),
                action(Wait(), "//*[@class=\"search-posts-container\"]")
            )
        ),
        search(
            "view list",
            listOf(
                action(Click(), "//*[@class=\"icon_view_list\"]"),
                action(Wait(), "//*[@class=\"posts posts_view_list\"]")
            )
        ),
        search(
            "view grid",
            listOf(
                action(Click(), "//*[@class=\"icon_view_grid\"]"),
                action(Wait(), "//*[@class=\"posts posts_view_masonry\"]")
            )
        ),
        search(
            "post",
            listOf(
                action(Click(), "//*[@class=\"icon_view_grid\"]"),
                action(Wait(), "//*[@class=\"posts posts_view_masonry\"]")
            )
        ),
        search(
            "filter post",
            listOf(
                action(Click(), "//*[@class=\"search_control_items\"]/div[2]"),
                action(Click(), "//*[@class=\"menu_item post_type post_text_filter\"]")
            )
        ),
        search(
            "discover-controls-wrapper -> recommended",
            listOf(
                action(Wait(),"//*[@class=\"types-tabs\"]/a[1]"),
                action(Click(), "//*[@class=\"types-tabs\"]/a[1]"),
                action(Wait(), "//*[@id=\"posts\"]")
            )
        ),
        search(
            "discover-controls-wrapper -> trending",
            listOf(
                action(Click(), "//*[@class=\"types-tabs\"]/a[2]"),
                action(Wait(), "//*[@id=\"posts\"]")
            )
        ),
        search(
            "discover-controls-wrapper -> staff picks",
            listOf(
                action(Click(), "//*[@class=\"types-tabs\"]/a[3]"),
                action(Wait(), "//*[@id=\"posts\"]")
            )
        ),
        search(
            "discover-controls-wrapper -> text",
            listOf(
                action(Click(), "//*[@class=\"types-tabs\"]/a[4]"),
                action(Wait(), "//*[@id=\"posts\"]")
            )
        ),
        search(
            "discover-controls-wrapper -> photos",
            listOf(
                action(Click(), "//*[@class=\"types-tabs\"]/a[5]"),
                action(Wait(), "//*[@id=\"posts\"]")
            )
        ),
        search(
            "discover-controls-wrapper -> gifts",
            listOf(
                action(Click(), "//*[@class=\"types-tabs\"]/a[6]"),
                action(Wait(), "//*[@id=\"posts\"]")
            )
        ),
        search(
            "discover-controls-wrapper -> quote",
            listOf(
                action(Click(), "//*[@class=\"types-tabs\"]/a[7]"),
                action(Wait(), "//*[@id=\"posts\"]")
            )
        ),
        search(
            "discover-controls-wrapper -> chats",
            listOf(
                action(Click(), "//*[@class=\"types-tabs\"]/a[8]"),
                action(Wait(), "//*[@id=\"posts\"]")
            )
        ),
        search(
            "discover-controls-wrapper -> audio",
            listOf(
                action(Click(), "//*[@class=\"types-tabs\"]/a[9]"),
                action(Wait(), "//*[@id=\"posts\"]")
            )
        ),
        search(
            "discover-controls-wrapper -> video",
            listOf(
                action(Click(), "//*[@class=\"types-tabs\"]/a[10]"),
                action(Wait(), "//*[@id=\"posts\"]")
            )
        ),
        search(
            "discover-controls-wrapper -> asks",
            listOf(
                action(Click(), "//*[@class=\"types-tabs\"]/a[11]"),
                action(Wait(), "//*[@id=\"posts\"]")
            )
        )
    )

    fun init(){
        webDriver.get("https://www.tumblr.com/dashboard")
        resultList.add(ResultString("INFO", "Search"))
        scriptList.forEach{
            resultList.add(Script(it.scripts, it.name, "success click on element ${it.name}"))
        }
    }
}