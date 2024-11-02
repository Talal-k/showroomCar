package com.example.carshowroom.mapper;


import com.example.carshowroom.dto.ShowroomDto;
import com.example.carshowroom.dto.ShowroomPostDto;
import com.example.carshowroom.dto.ShowroomUpdateDto;
import com.example.carshowroom.model.Showroom;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShowroomMapper {
    List<ShowroomDto> toDtoList(List<Showroom> showrooms);

    Showroom toEntity(ShowroomPostDto showroomPostDto);

    ShowroomDto toDto(Showroom showroom);
    Showroom toUpdateEntity(ShowroomUpdateDto showroomUpdateDto);
}
