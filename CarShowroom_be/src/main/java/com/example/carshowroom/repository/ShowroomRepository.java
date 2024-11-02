package com.example.carshowroom.repository;

import com.example.carshowroom.model.Showroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowroomRepository extends JpaRepository<Showroom, Long> {
    Optional<Showroom> findByCommercialRegistrationNumber(Long commercialRegistrationNumber);
}