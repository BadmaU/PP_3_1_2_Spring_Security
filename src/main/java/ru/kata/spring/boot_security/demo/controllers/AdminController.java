package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id,  Model model){
        model.addAttribute("user_id", userService.getUserById(id));
        return "users";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "save";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/{id}/update")
    public String updateUserById(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id){
        userService.updateUserById(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String removeUserById(@PathVariable("id") long id){
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}