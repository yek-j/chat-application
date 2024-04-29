package com.yekj.proejct.chatapplication.controller;

import com.yekj.proejct.chatapplication.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@Slf4j
public class ChatController {
    @Autowired
    private ChatService service;

    @MessageMapping("/greetings")
    @SendTo("/topic/greetings")
    public String handleGreetings(Principal principal) {
        String username = principal.getName();
        return username + "님 환영합니다!";
    }

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public String handleMessage(@Payload String message, Principal principal) {
        try {
            service.saveChatMessage(principal.getName(), message);
        } catch (Exception e) {
            return null;
        }
        return message;
    }
}
