package com.yekj.proejct.chatapplication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@Slf4j
public class ChatController {
    @MessageMapping("/greetings")
    @SendTo("/topic/greetings")
    public String handleGreetings(Principal principal) {
        String username = principal.getName();
        log.info(username);
        return username + "님 환영합니다!";
    }

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public String handleMessage(@Payload String message) {
        log.info(message);
        return message;
    }
}
