package com.yekj.proejct.chatapplication.controller;

import com.yekj.proejct.chatapplication.model.User;
import com.yekj.proejct.chatapplication.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/login")
    public String login() {
        return "/users/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "/users/signup";
    }

    @PostMapping("/signup")
    public String signup(String username, String password, Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        try {
            service.addUser(user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/users/signup";
        }
        return "/";
    }
}
