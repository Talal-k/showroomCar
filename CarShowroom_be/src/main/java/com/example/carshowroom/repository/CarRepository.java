package com.example.carshowroom.repository;

import com.example.carshowroom.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    Page<Car> findAllByShowroomName(String showroomName, Pageable pageable);
    Page<Car> findAllByMaker(String maker, Pageable pageable);
    Page<Car> findAllByShowroomNameAndMaker(String showroomName, String maker, Pageable pageable);
}