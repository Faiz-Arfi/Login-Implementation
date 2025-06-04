package com.arfiProjects.Login_Implementation.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arfiProjects.Login_Implementation.model.User;
import com.arfiProjects.Login_Implementation.service.UserService;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        
        return "register";
    }    @PostMapping("/do-register")
    public String doRegister(@RequestParam Map<String, String> allParams, Model model) {
        // allParams contains all form fields as key-value pairs
        System.out.println("Form fields: " + allParams);
        
        boolean registrationSuccess = true;
        String messageString = "";
        
        // Validate input fields
        if(allParams.isEmpty()) {
            registrationSuccess = false;
            messageString = "Registration failed! Please Enter all fields.";
        } else if(allParams.get("password").length() < 8) {
            registrationSuccess = false;
            messageString = "Registration failed! Password must be at least 8 characters.";
        } else if(!allParams.get("password").equals(allParams.get("confirmPassword"))) {
            registrationSuccess = false;
            messageString = "Registration failed! Passwords do not match.";
        } else if(userService.isEmailTaken(allParams.get("email"))) {
            registrationSuccess = false;
            messageString = "Registration failed! Email is already registered.";
        }

        if (registrationSuccess) {
            try {
                // Register the user in the database
                User user = userService.registerUser(
                    allParams.get("fullname"),
                    allParams.get("email"),
                    allParams.get("password")
                );
                model.addAttribute("successMessage", "Registration successful! Please log in.");
            } catch (Exception e) {
                registrationSuccess = false;
                messageString = "Registration failed! An error occurred: " + e.getMessage();
                model.addAttribute("failureMessage", messageString);
                return "register";
            }
        } else {
            model.addAttribute("failureMessage", messageString);
        }
        return "register";
    }

    // @GetMapping("/logout")
    // public String logout() {
    //     return "logout";
    // }
}
