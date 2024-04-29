package com.yekj.proejct.chatapplication.service;

import com.yekj.proejct.chatapplication.model.User;
import com.yekj.proejct.chatapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User addUser(User user) throws Exception{
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<User> findUser = repository.findByUsername(user.getUsername());

        if(findUser.isPresent()) {
            // 중복된 사용자 이름은 사용 불가능
            throw new Exception("중복된 사용자 이름입니다.");
        }

        return repository.save(user);
    }
}
