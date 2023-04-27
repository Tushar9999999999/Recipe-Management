package com.namruslan.springwebshopdemo.controller;

import com.namruslan.springwebshopdemo.entities.UserRepr;
import com.namruslan.springwebshopdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserRepr());
        return "register";
    }

    @PostMapping("/register")
    public String newUser(@ModelAttribute("user") @Valid UserRepr userRepr, BindingResult result) {

        System.out.print("New user {}" + userRepr);

        if (result.hasErrors()) {
            return "register";
        }

        if (!userRepr.getPassword().equals(userRepr.getMatchingPassword())) {
            result.rejectValue("password", "", "Пароли должны совпадать");
            return "register";
        }

        userService.create(userRepr);

        return "redirect:/login";
    }
}
