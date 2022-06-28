package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class ApplicationAPIController {
    private UserService userService;
    @Autowired
    ApplicationAPIController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/get_login")
    public User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof User) {
                return (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            }
        }
        return new User();
    }
    @GetMapping(value = "/get_users_table")
    public List<User> getUsersTable() {
        return userService.getUserList();
    }
    @GetMapping(value = "/get_user")
    public User getUser() {
        return userService.getUser(1L);
    }
    @GetMapping(value = "/add_user")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "Success";
    }
}
