package com.example.carshowroom.serviceImpl;

import com.example.carshowroom.dto.UserLoginPostDto;
import com.example.carshowroom.dto.UserPost;
import com.example.carshowroom.mapper.UserMapper;
import com.example.carshowroom.model.User;
import com.example.carshowroom.repository.UserRepository;
import com.example.carshowroom.security.JwtTokenUtils;
import com.example.carshowroom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenUtils jwtTokenUtils;
    @Override
    public String signIn(UserPost user) {
        User entity = this.userMapper.toEntity(user);
        entity.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        this.userRepository.save(entity);
        return this.jwtTokenUtils.generateToken(user.getUsername());
    }

    @Override
    public String signIn(UserLoginPostDto user) {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
         this.userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException(""));
        return this.jwtTokenUtils.generateToken(user.getUsername());
    }
}
