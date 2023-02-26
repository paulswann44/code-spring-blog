package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.BookRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private BookRepository bookDao;

    public BookController(BookRepository bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("allBooks", bookDao.findAll());

        // JPA Methods
        // .save() - insert or save a new record
        // .deleteById(long id) - deletes a record by ID
        // .findAll() - returns a List of all objects (records)
        // .findById(long id).get() - returns a single Object

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getUsername());
        return "books/index";
    }

}