package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.security.UserDetails;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping(value= {"/", "/index"})
    public String getIndexPage () {
        return "index";
    }
    @GetMapping(value="/user")
    public String getUserInfo(ModelMap model) {
        UserDetails userDetails = (UserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<String> roles = new ArrayList<>();
        for(Role role : userDetails.getUser().getRoles()) {
            roles.add(role.getRole());
        }
        model.addAttribute("userroles", roles);
        model.addAttribute("userinfo", userDetails);
        return "user";
    }
    @GetMapping
    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        model.addAttribute("error", e);
        return "error";
    }
}
