package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
public class PostController {



    @GetMapping("/posts")
    @ResponseBody
    public String postPage(){
        return "posts";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postId(@PathVariable int id){
        return postPage() + "/{"+ id +"}";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createGet(){
        return "posts/create";
    }

    @RequestMapping(path ="/posts/create" , method= RequestMethod.POST)
    @ResponseBody
    public String createPost(){
        return "create a new post";
}

}
