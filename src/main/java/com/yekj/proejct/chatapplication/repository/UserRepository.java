package com.yekj.proejct.chatapplication.repository;

import com.yekj.proejct.chatapplication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
