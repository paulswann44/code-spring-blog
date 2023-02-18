package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @RequestMapping(path = "/add/{num1}/and/{num2}")
    @ResponseBody
    public String add(@PathVariable int num1, @PathVariable int num2){
        int result = num1 + num2;
        return num1 + " + " + num2 + " = " + result;
    }

    @RequestMapping(path="/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String subtract(@PathVariable int num1 , @PathVariable int num2){
        int result = num1 - num2;
        return num1 + " - " + num2 + " = " + result;
    }

    @RequestMapping(path ="/multiply/{num1}/and/{num2}" )
    @ResponseBody
    public String multiply (@PathVariable int num1, @PathVariable int num2){
        int result = num1 * num2;
        return num1 + " * " + num2 + " = " + result;

    }

    @RequestMapping(path= "/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divide(@PathVariable int num1, @PathVariable int num2){
        int result = num1/ num2;
        return num1 + " / " + num2 + " = " + result;
    }


}
