package com.example.carshowroom.service;

import com.example.carshowroom.dto.ShowroomDto;
import com.example.carshowroom.dto.ShowroomPostDto;
import com.example.carshowroom.dto.ShowroomUpdateDto;
import com.example.carshowroom.filter.ShowroomPageRequest;
import org.springframework.data.domain.Page;


public interface ShowroomService {

    Page<ShowroomDto> findAllCarShowroom(ShowroomPageRequest request);

    ShowroomDto addCarShowroom(ShowroomPostDto postDto);
    ShowroomDto findCarShowroomDetails(Long id);
    ShowroomDto updateCarShowroom(ShowroomUpdateDto updateDto);
    boolean deleteCarShowroomById(Long id);

}
