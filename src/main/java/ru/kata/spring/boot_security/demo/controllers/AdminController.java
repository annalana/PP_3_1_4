//package ru.kata.spring.boot_security.demo.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.models.User;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import java.util.*;
//
//@RestController
//@RequestMapping(value = "/admin")
//public class AdminController {
//    private UserService userService;
//    @Autowired
//    AdminController(UserService userService) {
//        this.userService = userService;
//    }
//    @GetMapping
//    public User getAdiminApp(ModelMap model) {
//        User user = (User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        return user;
//    }
////    @GetMapping
////    public String getAdiminApp(ModelMap model) {
////        User user = (User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
////        model.addAttribute("current", user);
////        return "admin_panel";
////    }
//    @GetMapping(value = "/main")
//    public List<User> getAdminTable(ModelMap model) {
//        return userService.getUserList();
//    }
////    @GetMapping(value = "/main")
////    public String getAdminTable(ModelMap model) {
////        List<User> userList = userService.getUserList();
////        model.addAttribute("users", userList);
////        return "admin_table";
////    }
//    @GetMapping(value = "/adding_form")
//    public String getAddingForm(ModelMap model) {
//        model.addAttribute("newuser", new User());
//        return "add_user_form";
//    }
//    @GetMapping(value="/delete_form")
//    public String getDeleteForm(@RequestParam(value="id") long id, ModelMap model) {
//           model.addAttribute("user", userService.getUser(id));
//        return "delete_form";
//    }
//    @GetMapping(value = "/delete")
//    public ResponseEntity deleteUser(@RequestParam(value="id") long id,
//                                   ModelMap model) {
//        userService.removeUser(id);
//        return ResponseEntity.ok("Success");
//    }
////    @GetMapping(value="/delete")
////    public String deleteUser(@RequestParam(value = "id") long id, ModelMap model) {
////        System.out.println(id);
////        userService.removeUser(id);
////        return getAdminTable(model);
////    }
//    @GetMapping(value = "/add")
//    public ResponseEntity saveUser(@ModelAttribute User user,
//                                   ModelMap model) {
//        userService.addUser(user);
//        return ResponseEntity.ok("Success");
//    }
////    @GetMapping(value = "/add")
////    public String saveUser(@ModelAttribute User user,
////                           ModelMap model) {
////        userService.addUser(user);
////        return getAdminTable(model);
////    }
//
//    @GetMapping(value = "/redact")
//    public String redactionForm(@RequestParam(value="id") long id, ModelMap model) {
//        if (id != -1) {
//            model.addAttribute("user", userService.getUser(id));
//        }
//        return "edit_form";
//    }
//
//    @GetMapping(value = "/edit")
//    public ResponseEntity doRedact(@ModelAttribute User user,
//                                   ModelMap model) {
//        userService.redactUser(user.getId(), user);
//        return ResponseEntity.ok("Success");
//    }
////    @GetMapping(value = "/edit")
////    public String doRedact(@ModelAttribute User user,
////                           ModelMap model) {
////        userService.redactUser(user.getId(), user);
////        return getAdminTable(model);
////    }
//    @ExceptionHandler(Exception.class)
//    public String handleException(Model model, Exception e) {
//        e.printStackTrace();
//        model.addAttribute("error", e.getMessage());
//        return "error";
//    }
//}
