package com.nnk.springboot.poseidon.services;

import com.nnk.springboot.poseidon.domain.User;
import com.nnk.springboot.poseidon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ACrudService<UserRepository, User, Integer> {

    @Autowired
    public UserService(UserRepository repo) {
        super(repo);
    }

}