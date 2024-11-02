package com.example.carshowroom.mapper;

import com.example.carshowroom.dto.CarDto;
import com.example.carshowroom.model.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    List<CarDto> toDtoList(List<Car> carList);
}
