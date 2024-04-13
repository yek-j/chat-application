package com.yekj.proejct.chatapplication.service;

import com.yekj.proejct.chatapplication.model.User;
import com.yekj.proejct.chatapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return repository.save(user);
    }
}
