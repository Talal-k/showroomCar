package com.example.carshowroom.service;


import com.example.carshowroom.dto.UserLoginPostDto;
import com.example.carshowroom.dto.UserPost;

public interface UserService {

    String signIn(UserPost user);
    String signIn(UserLoginPostDto user);
}
