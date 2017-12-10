package com.github.rudge.poc.service.impl;

import com.github.rudge.poc.repository.UserRepository;
import com.github.rudge.poc.domain.User;
import com.github.rudge.poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {

        return (List<User>) userRepository.findAll();
    }

    @Override
    public User get(long id) {

        return userRepository.findById(id);
    }

    @Override
    public void remove(long id) {
        userRepository.delete(id);
    }

}
