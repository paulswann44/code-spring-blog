package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//creates getters, setters, and toString... Essentially a whole bean
record Message(String message){}

@Controller
public class HelloController {
    //Method will listen for GET request at /hello
    @GetMapping("/hello")
    @ResponseBody()
    public String helloWorld(){return "<h1>Hello from Spring</h1>";}

    //creates JSON response
    @GetMapping(path="json", produces = "application/json")
    @ResponseBody
    public Message returnMessage(){
        return new Message("Hello from Spring");
    }

    //We have a GetMapping, DeleteMapping, PostMapping => All HTTP methods
    @RequestMapping(path ="/color" , method= RequestMethod.GET)
    @ResponseBody
    public String color(){
        return "Royal Blue!";
    }
    @GetMapping("/hello/{firstName}/{lastName}")
    @ResponseBody
    public String sayHello(@PathVariable String firstName, @PathVariable String lastName){
        return "<h1> Hello " + firstName + " " + lastName + "!</h1>";

    }
}
