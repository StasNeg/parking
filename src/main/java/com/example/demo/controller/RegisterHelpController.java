package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/register") // This means URL's start with /demo (after Application path)
public class RegisterHelpController {


    private UserService userService;

    @Autowired
    public RegisterHelpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/uniqueEmail")
    public @ResponseBody
    String isUniqueEmail(@RequestParam String email, @RequestParam String id) {
        UserDto user = userService.findByEmail(email);
        if (user.isNew())
            return "true";
        else if (!id.isEmpty() && user.getId().equals(Long.parseLong(id)))
            return "true";
        return "false";
    }



}
