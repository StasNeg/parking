package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/admin") // This means URL's start with /demo (after Application path)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/save") // Map ONLY GET Requests
    public @ResponseBody
    UserDto createOrEditUser(@RequestBody UserDto user) {
        return new UserDto(userService.createOrEdit(user));
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody
    void deleteUser(@RequestParam String id) {
        userService.delete(Long.parseLong(id));
    }

    @GetMapping(path = "/all") // Map ONLY GET Requests
    public String all(Map<String, Object> model) {
        model.put("users", userService.getAll());
        return "users";
    }

    @GetMapping(path = "/email")
    public @ResponseBody
    UserDto getUserByEmail(@RequestParam String email) {
        // This returns a JSON or XML with the users
        return userService.findByEmail(email);
    }
}