package com.namruslan.springwebshopdemo.service;

import com.namruslan.springwebshopdemo.entities.Authority;
import com.namruslan.springwebshopdemo.entities.User;
import com.namruslan.springwebshopdemo.entities.UserRepr;
import com.namruslan.springwebshopdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(UserRepr userRepr) {
        User user = new User();
        user.setEnabled(true);
        user.setUsername(userRepr.getUsername());
        user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
        userRepository.save(user);
    }
}
