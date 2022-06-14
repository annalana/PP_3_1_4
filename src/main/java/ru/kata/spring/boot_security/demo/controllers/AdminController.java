package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.UserDetails;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping
    public String allUsersList(ModelMap model) {
        List<User> userList = userService.getUserList();
        model.addAttribute("users", userList);
        model.addAttribute("newuser", new User());
        return "allusers";
    }
    @GetMapping(value="/delete")
    public String deleteUser(@RequestParam(value="id") long id, ModelMap model) {
        try {
            userService.removeUser(id);
            model.addAttribute("message", "User by ID " + id + " deleted.");
        } catch (Exception e) {
            model.addAttribute("message", "ERROR. User was not deleted");
            e.printStackTrace();
        }
        return "userdeleted";
    }
    @GetMapping(value = "/add")
    public String saveUser(@ModelAttribute User user,
                           @RequestParam(value = "role_name_list") List<String> rolesNames,
                           ModelMap model) {
        List<Role> userroles = new ArrayList<>();
        for (String role1: rolesNames) {
            userroles.add(roleService.getRole(role1));
        }
        userService.addUser(new User(user, userroles));
        return allUsersList(model);
    }
    @GetMapping(value = "/redact")
    public String redactionForm(@RequestParam(value="id") long id, ModelMap model) {
        if (id != -1) {
            model.addAttribute("user", userService.getUser(id));
        }
        return "redactuser";
    }
    @GetMapping(value = "/do_redact")
    public String doRedact(@ModelAttribute User user,
                           @RequestParam(value = "role_name_list") List<String> rolesNames,
                           ModelMap model) {
        try {
            Set<Role> userroles = new HashSet<>();
            for (String role1: rolesNames) {
                userroles.add(roleService.getRole(role1));
            }
            model.addAttribute("redactionmessage", "Success. " +
                    userService.redactUser(user.getId(), user, userroles));
        } catch (Exception e) {
            model.addAttribute("redactionmessage", "Error while redacting");
            e.printStackTrace();
        }
        return "redactingresult";
    }
    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        UserDetails userDetails = (UserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<String> roles = new ArrayList<>();
        for(Role role : userDetails.getUser().getRoles()) {
            roles.add(role.getRole());
        }
        model.addAttribute("userroles", roles);
        model.addAttribute("error", e.getMessage() + "\n\n" + Arrays.toString(e.getStackTrace()));
        return "error";
    }
}
