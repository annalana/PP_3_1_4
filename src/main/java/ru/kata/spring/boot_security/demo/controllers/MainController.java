package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @GetMapping(value= {"/", "/index"})
    public String getIndexPage () {
        return "index";
    }
    @GetMapping(value= {"/login"})
    public String getLoginPage () {
        return "login";
    }
    @GetMapping(value="/user")
    public String getUserInfo(ModelMap model) {
        User user = (User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<String> roles = new ArrayList<>();
        for(Role role : user.getAuthorities()) {
            roles.add(role.getRole());
        }
        model.addAttribute("userroles", roles);
        model.addAttribute("userinfo", user);
        return "user";
    }
    @GetMapping
    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        model.addAttribute("error", e);
        return "error";
    }
}
