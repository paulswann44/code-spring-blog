package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;



// This is the PostController class that handles all the HTTP requests related to posts.
@Controller
public class PostController {
    // This is a private field of PostRepository type that is used to access the post data from the database.
    private PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    // This method maps the HTTP GET request to the "/posts" URL and returns the view with all the posts.
    @GetMapping("/posts")
    public String showPosts(Model model){
        // Adds all the posts to the model.
        model.addAttribute("allPosts", postDao.findAll());
        // Returns the view to display all the posts.
        return "posts/index";
    }

    // This method maps the HTTP GET request to the "/posts/{id}" URL and returns the view for a single post with the given ID.
    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model){
        // Finds the post with the given ID and adds it to the model.
        model.addAttribute("post", postDao.findById(id).get());
        // Returns the view to display a single post.
        return "posts/show";
    }

    // This method maps the HTTP GET request to the "/posts/create" URL and returns the view for creating a new post.
    @GetMapping("/posts/create")
    public String createGet(){
        // Returns the view to create a new post.
        return "posts/create";
    }

    // This method maps the HTTP POST request to the "/posts/create" URL and saves the new post to the database.
    @RequestMapping(path ="/posts/create" , method= RequestMethod.POST)
    public String createPost(@RequestParam String title, @RequestParam String body){
        // Creates a new post object with the given title and body.
        Post newPost = new Post(title, body);
        // Saves the new post to the database.
        postDao.save(newPost);
        // Redirects the user to the view with all the posts.
        return "redirect:/posts";
    }
}
