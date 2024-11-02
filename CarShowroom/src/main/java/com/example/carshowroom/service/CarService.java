package com.example.carshowroom.service;

import com.example.carshowroom.dto.CarDto;
import com.example.carshowroom.filter.CarPageRequest;
import org.springframework.data.domain.Page;

public interface CarService {

    Page<CarDto> findAllCars(CarPageRequest request);
}
