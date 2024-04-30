package com.yekj.proejct.chatapplication.service;

import com.yekj.proejct.chatapplication.model.ChatMessage;
import com.yekj.proejct.chatapplication.model.User;
import com.yekj.proejct.chatapplication.repository.ChatMessageRepository;
import com.yekj.proejct.chatapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    @Autowired
    private ChatMessageRepository chatRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveChatMessage(String sender, String message) throws Exception{
        Optional<User> user = userRepository.findByUsername(sender);

        if (user.isPresent()) {
            ChatMessage chatMessage = new ChatMessage();
            LocalDateTime now = LocalDateTime.now();

            chatMessage.setSender(user.get());
            chatMessage.setMessage(message);
            chatMessage.setDate(now);
            chatRepository.save(chatMessage);
        } else {
            throw new Exception("사용자를 찾을 수 없습니다.");
        }
    }

    public String[] getChatMessages() {
        List<ChatMessage> chatMessageList = chatRepository.findAll();

        String[] messages = new String[chatMessageList.size()];
        for (int i = 0; i < chatMessageList.size(); i++) {
            messages[i] = chatMessageList.get(i).getSender().getUsername() + " : " + chatMessageList.get(i).getMessage();
        }

        return messages;
    }
}
