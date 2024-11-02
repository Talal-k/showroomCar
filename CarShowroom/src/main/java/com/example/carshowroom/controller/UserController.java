package com.example.carshowroom.controller;

import com.example.carshowroom.dto.UserLoginPostDto;
import com.example.carshowroom.dto.UserPost;
import com.example.carshowroom.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    String registerUser(@RequestBody @Valid UserPost user){
       return this.userService.signIn(user);
    }
    @PostMapping("sign-in")
    String signIn(@RequestBody @Valid UserLoginPostDto user){
        return this.userService.signIn(user);
    }
}
