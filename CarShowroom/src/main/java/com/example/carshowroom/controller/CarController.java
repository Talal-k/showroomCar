package com.example.carshowroom.controller;

import com.example.carshowroom.dto.CarDto;
import com.example.carshowroom.filter.CarPageRequest;
import com.example.carshowroom.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {
    private final CarService carService;

    @PostMapping("pageable")
    public Page<CarDto> findAllCars(@RequestBody CarPageRequest request) {
        return this.carService.findAllCars(request);
    }
}
