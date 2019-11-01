package com.bot.vk.vkbot.core;

import com.bot.vk.vkbot.core.client.VkClient;
import com.bot.vk.vkbot.service.BanService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class VkClientImplTest {

    String str;
    String result;

    public VkClientImplTest(String str, String result){
        this.str = str;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection strings(){
        return Arrays.asList(new Object[] []{{"add", "Для того, чтобы добавить товар, необходимо ввести команду в формате и прикрепить к сообщению с командой фотографию:\\n/add \"название объявления\" \"описание\" категория цена\\n\\n• Текст названия и описания товара должны быть выделены кавычками и не содержать их в самом тексте\\n• Текст названия должен состоять не менее чем из 4 символов\\n• Текст описания должен состоять не менее чем из 10 символов\\n• Категория товара должна быть числом (подробнее /info category)\\n• Цена товара - число (если число вещественное, то разделение происходит точкой)\\n• Размер фотографии должен быть не менее 400x400 пикселей\n"},
                {"delete", "market.info.delete"},
                {"edit", "market.info.edit"},
                {"category", "market.info.category"},
                {"find", "market.info.find"},
                {"reply", "market.info.reply"},
                {"info", "market.info.info"}});
    }

    @Test
    public void getMarketInfo() throws InterruptedException{
        System.out.println("getMarketInfo");
        String expResult = new VkClientImpl().getMarketInfo(str);
        assertEquals(expResult, result);
    }
}