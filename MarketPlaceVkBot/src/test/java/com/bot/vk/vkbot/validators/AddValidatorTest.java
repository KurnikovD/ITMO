package com.bot.vk.vkbot.validators;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class AddValidatorTest {
    String str;
    boolean result;

    public AddValidatorTest(String str, boolean result){
        this.str = str;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection strings(){
        return Arrays.asList(new Object[] []{{"/add \"Название\" \"Описание более 10 символов\" 4 4500", true},
                {"add \"Название1233\" \"Команда должна начинаться со слеша\" 42 450000", false},
                {"/add \"Наз\" \"Описание\" 45878 4548751", false},
                {"/add \"Название должно быть менее 100 символов в длину11111111111111111111111111111111111111111111111111111111111111\"" +
                        "\"Описанеи\" 45645 78952", false}});
    }

    @Test
    public void isValid() throws InterruptedException{
        System.out.println("AddValidator");
        boolean expResult = new AddValidator().isValid(str);
        assertEquals(expResult, result);
    }
}