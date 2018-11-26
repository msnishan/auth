package com.msnishan.auth.user.service;

import com.msnishan.auth.user.domain.User;
import com.msnishan.auth.user.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

}
