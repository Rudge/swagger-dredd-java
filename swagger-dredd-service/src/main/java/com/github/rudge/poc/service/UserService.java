package com.github.rudge.poc.service;

import com.github.rudge.poc.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    
    void save(User user);
    
    List<User> getAll();
    
    User get(long id);
    
    void remove(long id);

}
