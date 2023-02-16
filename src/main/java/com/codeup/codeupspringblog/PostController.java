package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
        return "posts";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String id(@PathVariable int id){
        return posts() + "{"+ id +"}";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createGet(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return null;
}
}
