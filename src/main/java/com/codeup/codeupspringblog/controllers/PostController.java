package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
//import com.codeup.codeupspringblog.services.EmailService;
import com.codeup.codeupspringblog.services.PostDaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    private final PostDaoService postService;
//    private final EmailService emailService;

    public PostController(PostDaoService postService) {
        this.postService = postService;
//        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String showPosts(Model model){
        model.addAttribute("allPosts", postService.getAllPosts());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model){
        model.addAttribute("post", postService.getPostById(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String postCreateForm(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping(path = "/posts/create")
    public String postCreateSubmit(@ModelAttribute Post post){
        System.out.println(post.getId());
        System.out.println(post.getTitle());
        System.out.println(post.getBody());
        System.out.println(post.getUser());
        postService.savePost(post);
//        emailService.sendTextEmail(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        Post postToEdit = postService.getPostById(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String submitPostChanges(@PathVariable long id, @ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/posts/" + id;
    }


}