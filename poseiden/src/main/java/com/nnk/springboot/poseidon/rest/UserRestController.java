package com.nnk.springboot.poseidon.rest;

import com.nnk.springboot.poseidon.domain.User;
import com.nnk.springboot.poseidon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController extends ACrudRestController<UserService, User, Integer> {

    @Autowired
    public UserRestController(UserService crudService) {
        super(crudService);
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<User> getById(Integer integer) {
        return super.getById(integer);
    }

    @Override
    public ResponseEntity delete(Integer integer) {
        return super.delete(integer);
    }
}