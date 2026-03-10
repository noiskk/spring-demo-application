package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserDao userDao;

    public User login(String username, String password){
        User user = userDao.findByUsername(username);

        if(user != null && user.getPassword().equals(password)){
            return user;
        }

        return null;
    }
}