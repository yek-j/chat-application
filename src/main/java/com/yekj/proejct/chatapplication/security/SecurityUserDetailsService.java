package com.yekj.proejct.chatapplication.security;

import com.yekj.proejct.chatapplication.model.User;
import com.yekj.proejct.chatapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public SecurityUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException(username + " not found");

        User user = userRepository.findByUsername(username).orElseThrow(s);

        return new SecurityUserDetails(user);
    }
}
