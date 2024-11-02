package com.example.carshowroom.mapper;

import com.example.carshowroom.dto.UserPost;
import com.example.carshowroom.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserPost userPost);
}
