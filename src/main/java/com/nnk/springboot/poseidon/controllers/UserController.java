package com.nnk.springboot.poseidon.controllers;

import com.nnk.springboot.poseidon.domain.User;
import com.nnk.springboot.poseidon.mapper.ReflectMapper;
import com.nnk.springboot.poseidon.models.UserRegistrationModel;
import com.nnk.springboot.poseidon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final ReflectMapper<UserRegistrationModel, User> registrationMapper;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        registrationMapper= new ReflectMapper<>(UserRegistrationModel.class, User.class);
    }

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userService.reads());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(@ModelAttribute("user") User user) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@ModelAttribute("user") @Valid UserRegistrationModel user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            userService.save(registrationMapper.mapToEntity(user));
            model.addAttribute("users", userService.reads());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.read(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid @ModelAttribute("user") User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        userService.update(id, user);
        model.addAttribute("users", userService.reads());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteById(id);
        model.addAttribute("users", userService.reads());
        return "redirect:/user/list";
    }
}
