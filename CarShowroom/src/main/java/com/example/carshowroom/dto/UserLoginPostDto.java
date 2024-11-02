package com.example.carshowroom.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginPostDto {
    public String username;
    public String password;
}
