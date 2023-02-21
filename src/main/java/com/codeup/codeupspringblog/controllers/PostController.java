package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    //    List is all in memory
    // Declare and initialize a list of Post objects in memory
    List<Post> posts= new ArrayList<Post>(){
        {
        add(new Post(1,"Post 1", "This is my first body"));
        add(new Post(2, "Post 2", "This is my second body"));
        add(new Post(3, "Post 3", "This is my third body"));
        }
    };


    @GetMapping("/posts")

    //this is the file path
    public String showPosts(Model model){
        // Add the list of posts to the Model object
        model.addAttribute("allPosts", posts);

        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable int id, Model model){


        // Iterate through the list of posts to find the post with the matching ID
        for (Post post: posts) {
            // Add the matching post to the Model object
            if(post.getId() ==id){
                model.addAttribute("post",post);
            }

        }

        return "posts/show";
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
