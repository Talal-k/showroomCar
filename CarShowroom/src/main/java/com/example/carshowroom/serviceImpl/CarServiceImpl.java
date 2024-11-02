package com.example.carshowroom.serviceImpl;

import com.example.carshowroom.dto.CarDto;
import com.example.carshowroom.dto.ShowroomDto;
import com.example.carshowroom.filter.CarPageRequest;
import com.example.carshowroom.mapper.CarMapper;
import com.example.carshowroom.model.Car;
import com.example.carshowroom.repository.CarRepository;
import com.example.carshowroom.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    @Override
    public Page<CarDto> findAllCars(CarPageRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize(), request.getOrder(),request.getSortBy());
        Page<Car> entities = null;
        if (request.getCarShowroomName() != null && request.getMaker() != null) {
          entities =   this.carRepository.findAllByShowroomNameAndMaker(request.getCarShowroomName(), request.getMaker(), pageable);
        }else if (request.getCarShowroomName() != null) {
            entities =   this.carRepository.findAllByShowroomName(request.getCarShowroomName(), pageable);
        }else if (request.getMaker() != null) {
            entities =   this.carRepository.findAllByMaker(request.getMaker(), pageable);
        }else{
            entities = this.carRepository.findAll(pageable);
        }
        List<CarDto> dtoList = this.carMapper.toDtoList(entities.getContent());

        return new PageImpl<CarDto>(dtoList,pageable,entities.getTotalElements());
    }
}
