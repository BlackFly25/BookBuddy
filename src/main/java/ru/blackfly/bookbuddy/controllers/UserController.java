package ru.blackfly.bookbuddy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/test")
    public String test() {
        return "Место для вашей рекламы";
    }
}
