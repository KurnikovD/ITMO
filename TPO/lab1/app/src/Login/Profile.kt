package Login

import Clear
import Click
import ClickX
import Get
import JS
import ResultString
import Script
import Scroll
import SendKeys
import Wait
import action
import resultList
import webDriver

class Profile {
    data class profile(
        val name: String,
        val create: List<action>,
        val edit: List<action>,
        val delete: List<action> = listOf(
            action(Scroll(), ""),
            action(Wait(), "//*[@class=\"post_control post-control-icon post_control_menu creator\"]"),
            action(JS(), "//*[@title=\"Параметры поста\"]"),
            action(Wait(), "//*[@class=\"post_control delete show_label\"]"),
            action(JS(), "//*[@class=\"post_control delete show_label\"]"),
            action(Wait(), "//*[@class=\"ui_button btn_1 chrome blue\"]"),
            action(JS(), "//*[@class=\"ui_button btn_1 chrome blue\"]"),
            action(Wait(),"//*[@class=\"posts only_me\"]")
        )
    )

    private val scriptList : List<profile> = listOf(
        profile("post",
//Post
//create
            listOf(
                action(Click(),"//*[@class=\"icon_post_text\"]"),
                action(SendKeys(),"//*[@aria-label=\"Заголовок поста\"]",0,"My first POST"),
                action(SendKeys(),"//*[@aria-label=\"Текст поста\"]",0,"I don't know why i decided to create post"),
                action(Wait(),"//*[@class=\"button-area create_post_button\"]"),
                action(Click(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            ),
//edit
            listOf(
                action(Click(),"//*[@class=\"post_control post-control-icon post_control_menu creator\"]"),
                action(Click(),"//*[@class=\"post_control edit show_label\"]"),
                action(Clear(),"//*[@aria-label=\"Текст поста\"]"),
                action(SendKeys(),"//*[@aria-label=\"Текст поста\"]",0,"I don't know why I decided to create post, but i did it"),
                action(Click(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            )
        ),
        profile("photo",
//Photo
//create
            listOf(
                action(Click(),"//*[@class=\"icon_post_photo\"]"),
                action(Wait(),"//*[@class=\"dropzone-icon dropzone-add-url-icon\"]"),
                action(Click(),"//*[@class=\"dropzone-icon dropzone-add-url-icon\"]"),
                action(SendKeys(),"//*[@aria-label=\"Вставьте URL-адрес\"]",0,"https://habrastorage.org/webt/i2/sk/vx/i2skvxg58f8zsulutrppdzbl7hk.png\n"),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            ),
//edit
            listOf(
                action(Click(),"//*[@class=\"post_control post-control-icon post_control_menu creator\"]"),
                action(Click(),"//*[@class=\"post_control edit show_label\"]"),
                action(SendKeys(),"//*[@aria-label=\"Подпись\"]",0,"I forgot add description"),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            )
        ),
        profile("quote",
//Quote
//create
            listOf(
                action(Click(),"//*[@class=\"icon_post_quote\"]"),
                action(SendKeys(),"//*[@aria-label=\"Цитата\"]",0,"Хорошо, Java, ВОЗМОЖНО, хороший пример того как должен выглядеть язык. Но тогда программы на Java — это хороший пример как НЕЛЬЗЯ писать программы."),
                action(SendKeys(),"//*[@aria-label=\"Источник\"]",0,"pixadel"),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            ),
//edit
            listOf(
                action(Click(),"//*[@class=\"post_control post-control-icon post_control_menu creator\"]"),
                action(Click(),"//*[@class=\"post_control edit show_label\"]"),
                action(Clear(),"//*[@aria-label=\"Цитата\"]"),
                action(SendKeys(),"//*[@aria-label=\"Цитата\"]",0,"Линк, Линк, снова наступило кроволуние. Будь осторожен!"),
                action(Clear(),"//*[@aria-label=\"Источник\"]"),
                action(SendKeys(),"//*[@aria-label=\"Источник\"]",0,"Зельда"),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            )
        ),
        profile("link",
//Link
//create
            listOf(
                action(Wait(),"//*[@class=\"icon_post_link\"]"),
                action(Click(),"//*[@class=\"icon_post_link\"]"),
                action(SendKeys(),"//*[@aria-label=\"Введите или вставьте URL-адрес\"]", 0, "https://www.seleniumhq.org\n"),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            ),
//edit
            listOf(
                action(Click(),"//*[@class=\"post_control post-control-icon post_control_menu creator\"]"),
                action(Click(),"//*[@class=\"post_control edit show_label\"]"),
                action(SendKeys(),"//*[@aria-label=\"Описание\"]",0,"I forgot to add description"),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            )
        ),
//Chat
//create
        profile("chat",
            listOf(
                action(Wait(), "//*[@class=\"icon_post_chat\"]"),
                action(Click(),"//*[@class=\"icon_post_chat\"]"),
                action(SendKeys(),"//*[@aria-label=\"Заголовок поста\"]",0,"Чатик"),
                action(SendKeys(),"//*[@aria-label=\"Текст поста\"]",0,"Л.: Извините, я опоздал!\nШ.: А что случилось?"),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            ),
//edit
            listOf(
                action(Click(),"//*[@class=\"post_control post-control-icon post_control_menu creator\"]"),
                action(Click(),"//*[@class=\"post_control edit show_label\"]"),
                action(SendKeys(), "//*[@aria-label=\"Текст поста\"]",0,"\nЛ.: Да ничего, я просто не хотел приходить."),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            )
        ),
//Audio
//create
        profile("audio",
            listOf(
                action(Click(),"//*[@class=\"icon_post_audio\"]"),
                action(SendKeys(),"//*[@aria-label=\"Найдите песню или вставьте URL-адрес\"]",0,"Сыновья России кто пчелок уважает"),
                action(Wait(), "//*[@data-service=\"soundcloud\"]"),
                action(Click(),"//*[@data-service=\"soundcloud\"]/div[1]"),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            ),
//edit
            listOf(
                action(Scroll(), ""),
                action(Click(),"//*[@class=\"post_control post-control-icon post_control_menu creator\"]"),
                action(Click(),"//*[@class=\"post_control edit show_label\"]"),
                action(SendKeys(),"//*[@aria-label=\"Описание\"]",0,"О нет! опять она)))"),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            )
        ),
//Video
//create
        profile("video",
            listOf(
                action(Wait(), "//*[@class=\"icon_post_video\"]"),
                action(Click(),"//*[@class=\"icon_post_video\"]"),
                action(Wait(), "//*[@class=\"dropzone-icon dropzone-add-url-icon\"]"),
                action(Click(),"//*[@class=\"dropzone-icon dropzone-add-url-icon\"]"),
                action(SendKeys(),"//*[@aria-label=\"http://\"]",0,"https://youtu.be/y6120QOlsfU\n"),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            ),
//edit
            listOf(
                action(Scroll(), ""),
                action(Click(),"//*[@class=\"post_control post-control-icon post_control_menu creator\"]"),
                action(Wait(),"//*[@class=\"post_control edit show_label\"]"),
                action(Click(),"//*[@class=\"post_control edit show_label\"]"),
                action(SendKeys(),"//*[@aria-label=\"Подпись\"]",0,"no comments..."),
                action(JS(),"//*[@class=\"button-area create_post_button\"]"),
                action(Wait(),"//*[@class=\"post_container\"]")
            )
        )
    )

    fun init(){
       resultList.add(ResultString("INFO", "Profile"))

        for (script in scriptList){
//            if(webDriver.currentUrl != "https://www.tumblr.com/blog/santaclown6")
                webDriver.get("https://www.tumblr.com/blog/santaclown6")
            resultList.add(ResultString("INFO", "\t${script.name.toUpperCase()}"))
            resultList.add(Script(script.create, "\tCreate ${script.name}", "${script.name} created"))

            resultList.add(Script(script.edit, "\tEdit ${script.name}", "${script.name} changed"))
            resultList.add(Script(script.delete, "\tDelete ${script.name}", "${script.name} deleted"))
        }
    }
}