package com.nnk.springboot.poseidon.services;

import com.nnk.springboot.poseidon.domain.User;
import com.nnk.springboot.poseidon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ACrudService<UserRepository, User, Integer> {

    private final BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    public UserService(UserRepository repo, BCryptPasswordEncoder bCryptEncoder) {
        super(repo);
        this.bCryptEncoder = bCryptEncoder;
    }

    @Override
    public User save(User entity) {
        entity.setPassword(bCryptEncoder.encode(entity.getPassword()));
        return super.save(entity);
    }
}