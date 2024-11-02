package com.example.carshowroom.controller;

import com.example.carshowroom.dto.ShowroomDto;
import com.example.carshowroom.dto.ShowroomPostDto;
import com.example.carshowroom.dto.ShowroomUpdateDto;
import com.example.carshowroom.filter.ShowroomPageRequest;
import com.example.carshowroom.service.ShowroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/showroom")
@CrossOrigin(origins = "http://localhost:4200")
public class ShowroomController {
    private final ShowroomService showroomService;

    @PostMapping("pageable")
    public Page<ShowroomDto> findAllCarShowroom(@RequestBody ShowroomPageRequest request) {
        return this.showroomService.findAllCarShowroom(request);
    }
    @PostMapping("add")
    public ShowroomDto addCarShowroom(@RequestBody @Valid ShowroomPostDto postDto) {
        return this.showroomService.addCarShowroom(postDto);
    }
    @GetMapping("{id}")
    public ShowroomDto findCarShowroomDetails(@PathVariable Long id) {
        return this.showroomService.findCarShowroomDetails(id);
    }
    @PutMapping("update")
    public ShowroomDto updateCarShowroom(@RequestBody @Valid ShowroomUpdateDto updateDto) {
        return this.showroomService.updateCarShowroom(updateDto);
    }
    @DeleteMapping("delete/{id}")
    public boolean deleteCarShowroomById(@PathVariable Long id) {
       return this.showroomService.deleteCarShowroomById(id);
    }
}
