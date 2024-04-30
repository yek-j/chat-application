package com.yekj.proejct.chatapplication.controller;

import com.yekj.proejct.chatapplication.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    ChatService chatService;

    @GetMapping("/")
    public String mainPage(Model model) {
        String[] messages = chatService.getChatMessages();
        model.addAttribute("messages", messages);
        return "/index";
    }
}
