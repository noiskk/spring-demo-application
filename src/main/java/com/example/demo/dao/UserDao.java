package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    private static final List<User> userDatabase = new ArrayList<>();

    static {
        userDatabase.add(new User(1L, "user1", "1234"));
        userDatabase.add(new User(2L, "user2", "1234"));
    }

    public User findByUsername(String username){
        for(User user : userDatabase){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
