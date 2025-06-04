package com.arfiProjects.Login_Implementation.Controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

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
        if(allParams.isEmpty()) {
            registrationSuccess = false;
            messageString = "Registration failed! Please Enter all fields.";
        }
        if(allParams.get("password").length() < 6) {
            registrationSuccess = false;
            messageString = "Registration failed! Password must be at least 6 characters.";
        }
        if(!allParams.get("password").equals(allParams.get("confirmPassword"))) {
            registrationSuccess = false;
            messageString = "Registration failed! Passwords do not match.";
        }

        if (registrationSuccess) {
            model.addAttribute("successMessage", "Registration successful! Please log in.");
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
