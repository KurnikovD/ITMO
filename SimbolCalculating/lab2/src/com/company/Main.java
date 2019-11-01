package com.company;
import com.company.models.InputModel;
import com.company.objects.MathFuncSimplification;
import com.company.objects.ParseException;
import com.company.objects.Resource;


import java.io.Console;


public class Main {

    public static void main(String[] args) {
	    String inputJSONFileName = System.console().readLine("Input number :");

        Resource inputResource = new Resource(inputJSONFileName);

        InputModel inputModel = null;
        MathFuncSimplification mathFuncSimplification;

        try {
            inputModel = new InputModel(inputResource);
        }catch (NullPointerException el) {
            System.out.println("Error in parsing: " + el.getMessage());
        }

    }
}
