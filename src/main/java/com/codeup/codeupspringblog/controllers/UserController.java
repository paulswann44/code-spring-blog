package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    // Instantiate the dependencies required by UserController.
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    // Constructor to inject dependencies.
    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    // GET route to display the sign-up form.
    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        // Add a new, empty User object to the model for binding form data.
        model.addAttribute("user", new User());
        // Return the name of the template (JSP, Thymeleaf, etc.) that renders the sign-up form.
        return "users/sign-up";
    }

    // POST route to save a new user to the database.
    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        // Hash the user's password before saving it to the database.
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        // Save the new user to the database via the UserRepository.
        userDao.save(user);
        // Redirect the user to the login page upon successful signup.
        return "redirect:/login";
    }
}
