package com.example.carshowroom.dto;


import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPost {
    private String fullName;
    private String username;
    private String password;
}
