package com.yekj.proejct.chatapplication.repository;

import com.yekj.proejct.chatapplication.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}

