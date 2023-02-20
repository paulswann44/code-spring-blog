package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import java.util.*;

record Message(String message){}

@Controller
public class HelloController {

    //Method will listen for GET requests at /hello
    @GetMapping("/hello/{name}")

    public String helloWorld(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }
    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "join";
    }

    //creates JSON response
    @GetMapping(path = "json", produces ="application/json")
    @ResponseBody
    public Message returnMessage(){
        return new Message("Hello from Spring*");
    }

    //RequestMapping
    @RequestMapping(path = "/color", method = RequestMethod.GET)
    @ResponseBody
    public String color() {
        return "Purple!";
    }

    @GetMapping("/hello/{firstname}/{lastName}")
    @ResponseBody
    public String sayHello(@PathVariable String firstname, @PathVariable String lastName) {
        return "<h1>Hello " + firstname + " " + lastName + "!</h1>";
    }

    @GetMapping("/dogpark")
    public String dogpark(Model model) {
        List<String> dogs = new ArrayList<>();
        dogs.add("Achilles");
        dogs.add("Memnon");
        dogs.add("Zeus");

        model.addAttribute("dogs", dogs);
        return "dogPark";
    }

}