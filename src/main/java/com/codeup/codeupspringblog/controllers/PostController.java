package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.services.EmailService;
import com.codeup.codeupspringblog.services.PostDaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // Marks this class as a Spring MVC controller that handles HTTP requests
public class PostController {

    private final PostDaoService postService; // Declare an instance variable to hold a PostDaoService instance
    private final EmailService emailService; // Declare an instance variable to hold an EmailService instance

    public PostController(PostDaoService postService, EmailService emailService) { // Constructor-based dependency injection
        this.postService = postService; // Inject a PostDaoService instance into this class
        this.emailService = emailService; // Inject an EmailService instance into this class
    }

    @GetMapping("/posts") // Map HTTP GET requests to the /posts endpoint to this method
    public String showPosts(Model model){ // Uses Spring Model to pass data to the view
        model.addAttribute("allPosts", postService.getAllPosts()); // Call a method on the injected PostDaoService instance to get data and add it to the Model
        return "posts/index"; // Returns the name of the view that should be rendered
    }

    @GetMapping("/posts/{id}") // Map HTTP GET requests with a path variable to this method
    public String singlePost(@PathVariable long id, Model model){ // Uses a path variable to get the ID of the post and passes data to the view
        model.addAttribute("post", postService.getPostById(id)); // Call a method on the injected PostDaoService instance to get data and add it to the Model
        return "posts/show"; // Returns the name of the view that should be rendered
    }

    @GetMapping("/posts/create") // Map HTTP GET requests to the /posts/create endpoint to this method
    public String postCreateForm(Model model){ // Uses Spring Model to pass data to the view
        model.addAttribute("post", new Post()); // Creates a new Post object and adds it to the Model
        return "posts/create"; // Returns the name of the view that should be rendered
    }

    @PostMapping(path = "/posts/create") // Map HTTP POST requests to the /posts/create endpoint to this method
    public String postCreateSubmit(@ModelAttribute Post post){ // Uses @ModelAttribute to automatically bind HTTP POST parameters to the Post object
        postService.savePost(post); // Call a method on the injected PostDaoService instance to save the Post object to the database
        emailService.sendTextEmail(post); // Call a method on the injected EmailService instance to send an email
        return "redirect:/posts"; // Redirect the user to the /posts endpoint after the Post has been created
    }

    @GetMapping("/posts/{id}/edit") // Map HTTP GET requests with a path variable to this method
    public String showEditForm(@PathVariable long id, Model model) { // Uses a path variable to get the ID of the post and passes data to the view
        Post postToEdit = postService.getPostById(id); // Call a method on the injected PostDaoService instance to get data
        model.addAttribute("post", postToEdit); // Add the Post object to the Model
        return "posts/edit"; // Returns the name of the view that should be rendered
    }

    @PostMapping("/posts/{id}/edit") // Map HTTP POST requests with a path variable to this method
    public String submitPostChanges(@PathVariable long id, @ModelAttribute Post post) { // Uses a path variable to get the ID of the post and automatically bind HTTP POST parameters to the Post object
        postService.savePost(post); // Call a method on the injected PostDaoService instance to save the Post object to the database
        return "redirect:/posts/";

    }
}
