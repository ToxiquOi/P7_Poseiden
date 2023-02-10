package com.nnk.springboot.poseidon.controllers.rest;

import com.nnk.springboot.poseidon.domain.User;
import com.nnk.springboot.poseidon.models.UserRegistrationModel;
import com.nnk.springboot.poseidon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserRestController extends ACrudCustomSaveRestController<UserService, User, Integer, UserRegistrationModel> {

    @Autowired
    public UserRestController(UserService crudService) {
        super(crudService, UserRegistrationModel.class, User.class);
    }

    @Override
    @PostMapping("/save")
    public ResponseEntity<User> save(@Valid @NotNull UserRegistrationModel model) {
        return super.save(model);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable @NotNull Integer id) {
        return super.getById(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable @NotNull Integer id) {
        return super.delete(id);
    }
}